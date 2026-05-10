import React, { useEffect, useState } from 'react';
import { obtenerTodosLosReportes, eliminarReporte } from '../service/PropietarioService';
import '../styles/Home.css';

const Home = () => {
  const [reportes, setReportes] = useState([]);
  const [loading, setLoading] = useState(true);

  const cargarDatos = async () => {
    try {
      const data = await obtenerTodosLosReportes();
      setReportes(data);
    } catch (error) { console.error("Error cargando datos:", error); } 
    finally { setLoading(false); }
  };

  useEffect(() => { cargarDatos(); }, []);

  const handleEliminar = async (id) => {
    if (window.confirm("¿Confirmas que la mascota ya apareció?")) {
      try {
        await eliminarReporte(id);
        setReportes(reportes.filter(r => r.id !== id));
      } catch (error) { alert("No se pudo eliminar el reporte."); }
    }
  };

  return (
    <div className="home-container">
      <h1>🐾 Comunidad Mascotas Bien</h1>

      {loading ? <p>Cargando reportes...</p> : (
        <div className="reportes-grid">
          {reportes.map((reporte) => (
            <div key={reporte.id} className="mascota-card">
              
              <div className="card-image-container" style={{ backgroundColor: '#f0f0f0', height: '220px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                {/* BLINDAJE: Solo intentamos cargar si el String es suficientemente largo */}
                {reporte.image && reporte.image.length > 100 ? (
                  <img 
                    src={reporte.image} 
                    alt="Mascota"
                    className="mascota-img"
                    style={{ width: '100%', height: '100%', objectFit: 'cover' }}
                    onError={(e) => {
                       // Si la imagen falla, ocultamos el tag roto y mostramos el emoji
                       e.target.style.display = 'none';
                       e.target.parentNode.innerHTML = '<span style="font-size: 60px;">🐶</span>';
                    }}
                  />
                ) : (
                  <span style={{ fontSize: '60px' }}>🐶</span>
                )}
              </div>

              <div className="card-body">
                <span className={`badge ${reporte.estadoBusqueda}`}>{reporte.estadoBusqueda}</span>
                <h3>{reporte.nameMascota || "Mascota sin nombre"}</h3>
                <p>📍 <strong>Visto en:</strong> {reporte.address || "No informada"}</p>
                
                <div className="contacto-info">
                   <p><strong>Dueño:</strong> {reporte.name}</p>
                   <div className="botones-contacto">
                      <a href={`tel:${reporte.phoneNumber}`} className="btn-contacto call">
                        📞 {reporte.phoneNumber || "Llamar"}
                      </a>
                      <a href={`mailto:${reporte.email}`} className="btn-contacto mail">
                        ✉️ {reporte.email || "Email"}
                      </a>
                   </div>
                </div>

                <button className="btn-encontrado" onClick={() => handleEliminar(reporte.id)}>
                  🎉 ¡Ya se encontró!
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Home;