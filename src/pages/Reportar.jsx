import React, { useState } from 'react';
import { registrarPropietarioYPet } from '../service/PropietarioService';
import '../styles/Reportar.css';

const Reportar = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phoneNumber: '+569',
    address: '',
    tipoPropietario: 'NATURAL', // Enum Java
    estadoCuenta: 'ACTIVO',    // Enum Java
    estadoBusqueda: 'BUSCANDO', // Enum Java
    mascotaNombre: '',
    ubicacion: ''
  });

  // ESTADO NUEVO PARA LA IMAGEN BINARIA
  const [imageFile, setImageFile] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 1. CREAMOS EL FORMDATA (El "sobre mixto")
const data = new FormData();
data.append('name', formData.name);
data.append('email', formData.email);
data.append('phoneNumber', formData.phoneNumber);

// CAMBIO AQUÍ: Usa el nombre exacto que espera tu DTO de Java
data.append('address', formData.ubicacion); // Mapeamos 'ubicacion' a 'address'
data.append('nameMascota', formData.mascotaNombre); // Mapeamos a 'nameMascota'

data.append('tipoPropietario', formData.tipoPropietario);
data.append('estadoCuenta', 'ACTIVO');
data.append('estadoBusqueda', formData.estadoBusqueda);

if (imageFile) {
  data.append('image', imageFile); // 'image' debe ser igual al @RequestParam del Controller
}
    try {
      // Enviamos el FormData, no el JSON
      await registrarPropietarioYPet(data);
      alert(`¡Éxito! ${formData.mascotaNombre} y tú han sido reportados dinámicamente en Mascotas Bien.`);
    } catch (err) {
      console.error(err);
      alert("Error 400 o de conexión. Revisa el formato del teléfono (+569XXXXXXXX) o si la imagen es muy pesada (max 5MB).");
    }
  };

  return (
    <div className="reportar-container">
      <h2>📢 Registro de Reporte Dinámico</h2>
      <form onSubmit={handleSubmit} className="reportar-form">
        
        {/* Sección Propietario (Datos Obligatorios para DTO) */}
        <input type="text" placeholder="Tu Nombre Completo" value={formData.name} onChange={(e) => setFormData({...formData, name: e.target.value})} required />
        <input type="email" placeholder="Tu Correo" value={formData.email} onChange={(e) => setFormData({...formData, email: e.target.value})} required />
        <input type="text" placeholder="Tu Teléfono (+569...)" value={formData.phoneNumber} onChange={(e) => setFormData({...formData, phoneNumber: e.target.value})} required />
        
        {/* Selector Tipo Dueño */}
        <label>Tipo de Usuario:</label>
        <select value={formData.tipoPropietario} onChange={(e) => setFormData({...formData, tipoPropietario: e.target.value})}>
          <option value="NATURAL">Persona Natural</option>
          <option value="JURIDICO">Empresa</option>
          <option value="FUNDACION">Fundación</option>
        </select>

        <hr />

        {/* Sección Mascota (Para la Lógica de Búsqueda) */}
        <input type="text" placeholder="Nombre de la Mascota" value={formData.mascotaNombre} onChange={(e) => setFormData({...formData, mascotaNombre: e.target.value})} required />
        
        {/* Selector Estado Búsqueda */}
        <label>Estado de la Mascota:</label>
        <select value={formData.estadoBusqueda} onChange={(e) => setFormData({...formData, estadoBusqueda: e.target.value})}>
          <option value="BUSCANDO">En búsqueda</option>
          <option value="ENCONTRADA">Ya la encontré</option>
        </select>

        <input type="text" placeholder="Ubicación donde se perdió" value={formData.ubicacion} onChange={(e) => setFormData({...formData, ubicacion: e.target.value})} required />
        
        {/* NUEVO INPUT PARA LA FOTO */}
        <label className="label-file">Foto de la mascota (max 5MB):</label>
        <input 
          type="file" 
          accept="image/*" // Solo acepta imágenes
          onChange={(e) => setImageFile(e.target.files[0])} // Guardamos el binario
          required // Obligatorio para que la web sirva para algo
        />
        
        <button type="submit" className="btn-enviar">Registrar Reporte</button>
      </form>
    </div>
  );
};

export default Reportar;