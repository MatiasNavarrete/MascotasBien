import '../styles/PetCard.css';
import React from 'react';

const PetCard = ({ mascota }) => {
  // Verificamos si es "Perdido" para cambiar el color del badge después
  const esPerdido = mascota.estado === 'Perdido';

  return (
    <div className="pet-card">
      <div className="image-container">
        {/* Aquí mascota.imagen traerá la ruta que definiste (ej: /fotos/luna.jpg) */}
        <img src={mascota.imagen} alt={mascota.nombre} />
        
        {/* El badge cambia de clase según el estado */}
        <span className={`status-badge ${esPerdido ? 'badge-alert' : 'badge-success'}`}>
          {mascota.estado}
        </span>
      </div>

      <div className="pet-info">
        <h3>{mascota.nombre}</h3>
        <p className="location">📍 {mascota.ubicacion}</p>
        <p className="description">{mascota.descripcion}</p>
        
        <button className="btn-detail">Ver más información</button>
      </div>
    </div>
  );
};

export default PetCard;