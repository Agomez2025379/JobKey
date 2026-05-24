function filtrarInstituciones() {
    const input = document.getElementById('searchInput');
    if (!input) return;

    const filter = input.value.toUpperCase();
    const cards = document.querySelectorAll('.card-institution');

    cards.forEach(card => {
        const nombre = card.getAttribute('data-nombre') || '';
        const tipo = card.getAttribute('data-tipo') || '';

        if (nombre.toUpperCase().indexOf(filter) > -1 || tipo.toUpperCase().indexOf(filter) > -1) {
            card.style.display = '';
        } else {
            card.style.display = 'none';
        }
    });
}