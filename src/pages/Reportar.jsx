import React, { useState } from 'react';
import { registrarPropietarioYPet } from '../service/PropietarioService';
import '../styles/Reportar.css';

const Reportar = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phoneNumber: '+569',
    address: '',
    tipoPropietario: 'NATURAL', // Valor inicial de Enum
    estadoCuenta: 'ACTIVO',    // Valor inicial de Enum
    estadoBusqueda: 'BUSCANDO', // Valor inicial de Enum
    mascotaNombre: '',
    ubicacion: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Filtramos solo los datos que el Backend (DTO) acepta
    const dataToSubmit = {
      name: formData.name,
      email: formData.email,
      phoneNumber: formData.phoneNumber,
      address: formData.address,
      tipoPropietario: formData.tipoPropietario,
      estadoCuenta: formData.estadoCuenta,
      estadoBusqueda: formData.estadoBusqueda
    };

    try {
      await registrarPropietarioYPet(dataToSubmit);
      // Mensaje actualizado para el usuario final
      alert(`¡Felicidades! ${formData.name}, tú y ${formData.mascotaNombre} han sido registrados en Mascotas Bien.`);
    } catch (err) {
      alert("Hubo un problema con el registro. Revisa los datos e intenta nuevamente.");
    }
  };

  return (
    <div className="reportar-container">
      <h2>📢 Registro de Reporte</h2>
      <form onSubmit={handleSubmit} className="reportar-form">
        
        {/* Datos del Propietario */}
        <input type="text" placeholder="Tu Nombre Completo" value={formData.name} onChange={(e) => setFormData({...formData, name: e.target.value})} required />
        <input type="email" placeholder="Tu Correo" value={formData.email} onChange={(e) => setFormData({...formData, email: e.target.value})} required />
        <input type="text" placeholder="Tu Teléfono (+569...)" value={formData.phoneNumber} onChange={(e) => setFormData({...formData, phoneNumber: e.target.value})} required />
        
        {/* Selector de Tipo de Propietario */}
        <label>Tipo de Usuario:</label>
        <select value={formData.tipoPropietario} onChange={(e) => setFormData({...formData, tipoPropietario: e.target.value})}>
          <option value="NATURAL">Persona Natural</option>
          <option value="JURIDICO">Empresa (Jurídico)</option>
          <option value="FUNDACION">Fundación</option>
        </select>

        <hr />

        {/* Datos de la Mascota */}
        <input type="text" placeholder="Nombre de la Mascota" value={formData.mascotaNombre} onChange={(e) => setFormData({...formData, mascotaNombre: e.target.value})} required />
        
        {/* Selector de Estado de Búsqueda */}
        <label>Estado de la Mascota:</label>
        <select value={formData.estadoBusqueda} onChange={(e) => setFormData({...formData, estadoBusqueda: e.target.value})}>
          <option value="BUSCANDO">En búsqueda</option>
          <option value="ENCONTRADA">Ya la encontré</option>
          <option value="SIN_MASCOTAS_PERDIDAS">Sin reportes pendientes</option>
        </select>

        <input type="text" placeholder="Ubicación donde se perdió" value={formData.ubicacion} onChange={(e) => setFormData({...formData, ubicacion: e.target.value})} required />
        
        <button type="submit" className="btn-enviar">Registrar Reporte</button>
      </form>
    </div>
  );
};

export default Reportar;