import axios from "axios";

// Configuración de Axios para el puerto 8082
const api8082 = axios.create({
  baseURL: "http://172.17.0.1:8082", // URL base del backend para el puerto 8082
  timeout: 10000, // Tiempo máximo para una solicitud (en milisegundos)
});

// Función para enviar multipart/form-data
api8082.sendFormData = async (url, formData) => {
  try {
    const response = await api8082.post(url, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response;
  } catch (error) {
    console.error("Error al enviar FormData:", error);
    throw error;
  }
};

// Función para realizar solicitudes GET
api8082.getData = async (url, params = {}) => {
  try {
    const response = await api8082.get(url, { params });
    return response;
  } catch (error) {
    console.error("Error al realizar solicitud GET:", error);
    throw error;
  }
};

export default api8082;
