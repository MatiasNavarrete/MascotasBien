import React, { useState } from 'react';
import PetCard from '../components/PetCard';
import '../styles/Home.css';

const Home = () => {
  // Estos datos simulan lo que vendrá de tu base de datos de Java/Kotlin
  const [mascotas] = useState([
    { 
      id: 1, 
      nombre: 'Gerardo', 
      estado: 'Perdido', 
      ubicacion: 'La Calera', 
      descripcion: 'Pollo algo mal alimentado, se perdió el lunes pasado.',
      imagen: '/public/fotos/Poyo.jpeg'
    },
    { 
      id: 2, 
      nombre: 'Pastelito', 
      estado: 'Encontrado', 
      ubicacion: 'Viña del Mar', 
      descripcion: 'Perro encontrado cerca de la playa, parece que se perdió hace poco.',
      imagen: '/public/fotos/perro.jpg' 
    },
    { 
      id: 3, 
      nombre: 'Cabezon', 
      estado: 'Perdido', 
      ubicacion: 'Quillota', 
      descripcion: 'Busco a mi jirafa que se perdio en el campo ',
      imagen: '/public/fotos/jirafa.jpg'
    }
  ]);

  return (
    <div className="home-container">
      <header className="home-header">
        <h1>Mascotas Bien</h1>
        <p>Conectando corazones para que ninguna mascota se quede fuera.</p>
      </header>

      <section className="pet-grid">
        {mascotas.map((pet) => (
          <PetCard key={pet.id} mascota={pet} />
        ))}
      </section>
    </div>
  );
};

export default Home;