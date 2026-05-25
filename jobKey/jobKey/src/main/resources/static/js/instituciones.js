function edit(id) {
    window.location.href = '/instituciones/editar/' + id;
}

function confirmarEliminar(url) {
    return confirm('¿Estás seguro de eliminar esta institución?');
}

function filtrarInstituciones() {
    const input = document.getElementById('searchInput');
    if (!input) return;

    const filter = input.value.toUpperCase();
    const table = document.getElementById('tablaInstituciones');
    if (!table) return;

    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const nombreCell = rows[i].getElementsByTagName('td')[2];
        const tipoCell = rows[i].getElementsByTagName('td')[3];

        if (nombreCell || tipoCell) {
            const nombre = nombreCell ? nombreCell.textContent || nombreCell.innerText : '';
            const tipo = tipoCell ? tipoCell.textContent || tipoCell.innerText : '';

            if (nombre.toUpperCase().indexOf(filter) > -1 || tipo.toUpperCase().indexOf(filter) > -1) {
                rows[i].style.display = '';
            } else {
                rows[i].style.display = 'none';
            }
        }
    }
}

function convertirBase64(input) {
    const file = input.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        const base64 = e.target.result.split(',')[1];
        document.getElementById('logoBase64').value = base64;

        const preview = document.getElementById('logoPreview');
        preview.src = e.target.result;
        preview.style.display = 'block';
    };
    reader.readAsDataURL(file);
}

document.addEventListener('DOMContentLoaded', function() {
    const header = document.querySelector('.header');
    if (header && !document.getElementById('searchInput')) {
        const searchDiv = document.createElement('div');
        searchDiv.style.marginTop = '1rem';
        searchDiv.innerHTML = `
            <input type="text" id="searchInput" placeholder="🔍 Buscar por nombre o tipo..."
                   style="padding: 0.5rem; width: 100%; max-width: 300px; border-radius: 6px; border: 1px solid #E2E8F0;"
                   onkeyup="filtrarInstituciones()">
        `;
        header.appendChild(searchDiv);
    }
});