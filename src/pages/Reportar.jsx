import React, { useState } from 'react';
import '../styles/Reportar.css';

const Reportar = () => {
  const [formData, setFormData] = useState({
    nombre: '',
    estado: 'Perdido',
    ubicacion: '',
    descripcion: ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Datos para el BFF:", formData);
    alert("¡Mascota reportada! (Aquí conectaremos con tu Backend)");
  };

  return (
    <div className="reportar-container">
      <h2>📢 Reportar Mascota</h2>
      <form onSubmit={handleSubmit} className="reportar-form">
        <input 
          type="text" 
          placeholder="Nombre de la mascota" 
          onChange={(e) => setFormData({...formData, nombre: e.target.value})}
          required 
        />
        <select onChange={(e) => setFormData({...formData, estado: e.target.value})}>
          <option value="Perdido">Perdido</option>
          <option value="En búsqueda">En búsqueda</option>
          <option value="Encontrado">Encontrado</option>
        </select>
        <input 
          type="text" 
          placeholder="Ubicación (Ej: La Calera)" 
          onChange={(e) => setFormData({...formData, ubicacion: e.target.value})}
          required 
        />
        <textarea 
          placeholder="Descripción" 
          onChange={(e) => setFormData({...formData, descripcion: e.target.value})}
        ></textarea>
        <button type="submit" className="btn-enviar">Publicar Reporte</button>
      </form>
    </div>
  );
};

export default Reportar;