import '../styles/PetCard.css';

const PetCard = ({ mascota }) => {
  const statusClass = mascota.estado === 'Perdido' ? 'status-lost' : 'status-found';

  return (
    <div className="pet-card">
      <div className="image-wrapper">
        <img src={mascota.imagen} alt={mascota.nombre} />
        <span className={`badge ${statusClass}`}>{mascota.estado}</span>
      </div>
      <div className="pet-info">
        <h3>{mascota.nombre}</h3>
        <p>📍 {mascota.ubicacion}</p>
      </div>
    </div>
  );
};
export default PetCard;