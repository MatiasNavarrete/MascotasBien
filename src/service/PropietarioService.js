import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/propietario";

export const registrarPropietarioYPet = async (formData) => {
    const response = await axios.post(API_URL, formData,);
    return response.data;
};
export const obtenerTodosLosReportes = async () => {
  // Asegúrate de que esta URL sea la misma del @RequestMapping de tu Controller
  const response = await axios.get('http://localhost:8080/api/v1/propietario');
  return response.data; // Aquí llega la List<PropietarioResponseDto>
};

export const eliminarReporte = async (id) => {
    try {
  await axios.delete(`http://localhost:8080/api/v1/propietario/${id}`);
    } catch (error) {
        console.error("Error al eliminar el reporte:", error);
        throw error; 
    }
}