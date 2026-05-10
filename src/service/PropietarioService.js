import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/propietario";

export const registrarPropietarioYPet = async (datos) => {
    try {
            const response = await axios.post(API_URL, datos);
            return response.data;
    }
    catch (error) {
            console.error("Error al conectar al microservicio", error);
            throw error;
    }
};