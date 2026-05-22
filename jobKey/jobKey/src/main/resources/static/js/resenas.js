let selectedRating = 0;

document.addEventListener("DOMContentLoaded", () => {

    const urlParams = new URLSearchParams(window.location.search);
    const ofertaId = urlParams.get('ofertaId');

    if (ofertaId) {
        document.getElementById('ofertaId').value = ofertaId;
    }


    const stars = document.querySelectorAll('.star-input');

    stars.forEach(star => {
        star.addEventListener('click', (e) => {
            selectedRating = parseInt(e.target.getAttribute('data-value'));
            updateStarsDisplay(stars, selectedRating);
        });
    });


    loadReviewsFeed();


    const reviewForm = document.getElementById('reviewForm');
    reviewForm.addEventListener('submit', (e) => {
        e.preventDefault();

        if (selectedRating === 0) {
            alert("Por favor, selecciona una calificación en estrellas.");
            return;
        }


        const reviewPayload = {
            tipo: "candidato_a_empresa",
            ofertaId: document.getElementById('ofertaId').value ? parseInt(document.getElementById('ofertaId').value) : null,
            puntuacion: selectedRating,
            comentario: document.getElementById('comentario').value.trim()
        };


        fetch('/api/resenas/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reviewPayload)
        })
        .then(response => {
            if (!response.ok) throw new Error("Error en el servidor al almacenar la reseña.");
            return response.json();
        })
        .then(() => {
            alert("Reseña anónima registrada exitosamente.");


            reviewForm.reset();
            selectedRating = 0;
            updateStarsDisplay(stars, 0);


            loadReviewsFeed();
        })
        .catch(error => {
            console.error("Error al procesar el guardado:", error);
            alert("Hubo un contratiempo al procesar tu opinión. Inténtalo de nuevo.");
        });
    });
});

function updateStarsDisplay(starsArray, ratingValue) {
    starsArray.forEach(star => {
        const starValue = parseInt(star.getAttribute('data-value'));
        if (starValue <= ratingValue) {
            star.classList.add('active');
        } else {
            star.classList.remove('active');
        }
    });
}

function loadReviewsFeed() {
    const listContainer = document.getElementById('reviewsList');
    listContainer.innerHTML = `<p class="loading-text">Sincronizando feed de opiniones...</p>`;

    fetch('/api/resenas/get')
        .then(response => {
            if (!response.ok) throw new Error("Error de respuesta.");
            return response.json();
        })
        .then(data => {
            listContainer.innerHTML = "";

            if (data.length === 0) {
                listContainer.innerHTML = `<p class="loading-text">No hay opiniones registradas para este flujo de datos.</p>`;
                return;
            }

            data.forEach(item => {

                const starsGraphic = "★".repeat(item.puntuacion) + "☆".repeat(5 - item.puntuacion);

                const card = document.createElement('div');
                card.className = "review-card-item";
                card.innerHTML = `
                    <div class="review-card-header">
                        <span> Usuario Anónimo</span>
                        <span class="stars-display">${starsGraphic}</span>
                    </div>
                    <p class="comment-text">"${item.comentario || 'Únicamente asignó una puntuación en estrellas.'}"</p>
                `;
                listContainer.appendChild(card);
            });
        })
        .catch(err => {
            console.error("Error al cargar el feed:", err);
            listContainer.innerHTML = `<p class="loading-text">No se pudieron cargar las valoraciones en este momento.</p>`;
        });
}