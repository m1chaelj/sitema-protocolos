package mx.com.Escom_TT.Escom.external.rest.controller;

import mx.com.Escom_TT.Escom.core.business.input.EstadoFinalProtocoloService;
import mx.com.Escom_TT.Escom.core.business.input.ProtocoloService;
import mx.com.Escom_TT.Escom.core.entity.Sinodal;
import mx.com.Escom_TT.Escom.external.rest.dto.*;
import mx.com.Escom_TT.util.error.ErrorMapper;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;
import java.util.stream.Collectors;



@Path("/secretario/vista-distribucion-protocolos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Gestion de usuarios")
public class VistaSecretarioController {

    @Inject
    ProtocoloService protocoloService;

    @Inject
    EstadoFinalProtocoloService estadoFinalProtocoloService;

    @GET
    public Response todosProtocolos() {
        var protocolos = protocoloService.busquedaTodosProtocolos().stream().map(ProtocoloDto::fromEntity).collect(Collectors.toList());
        return Response.ok(protocolos).build();
    }

    @GET
    @Path("/sinodales/{academia}")
    public Response sinodalesProtocolos(@PathParam("academia") String academia) {
        var sinodal= protocoloService.busquedaSinodalesAcademia(academia).stream().map(SinodalDto::fromEntity).collect(Collectors.toList());
        return Response.ok(sinodal).build();
    }

    @POST
    @Path("/envio-datos")
    public Response envioDatos(@Valid EstadoFinalProtocoloPersistDto estadoFinalProtocoloPersistDto) {
        return estadoFinalProtocoloService.create(estadoFinalProtocoloPersistDto.toEntity())
                .map(EstadoFinalProtocoloDto::fromEntity).map(Response::ok).map(Response.ResponseBuilder::build)
                .getOrElseGet(error->ErrorMapper.errorCodeToResponseBuilder(error).build());
    }
}
