package mx.com.Escom_TT.Escom.core.business.implementation;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import mx.com.Escom_TT.Escom.core.business.input.IntegranteService;
import mx.com.Escom_TT.Escom.core.business.output.IntegranteRepository;
import mx.com.Escom_TT.Escom.core.entity.Integrante;
import mx.com.Escom_TT.util.error.ErrorCodesEnum;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class IntegranteBs implements IntegranteService {

@Inject
IntegranteRepository integranteRepository;

    @Override
    public Either<ErrorCodesEnum, Integrante> create(Integrante entity) {
        Either<ErrorCodesEnum, Integrante> result;

        String boletaPattern = "\\d{10}";

     if (!entity.getBoleta().toString().matches(boletaPattern)
                || !entity.getBoletaSegundoIntegrante().toString().matches(boletaPattern)) {
            result = Either.left(ErrorCodesEnum.RNS001);
        }
        else {
            Integrante integrantePersist = integranteRepository.save(entity);
            result = Either.right(integrantePersist);
        }

        return result;
    }



}
