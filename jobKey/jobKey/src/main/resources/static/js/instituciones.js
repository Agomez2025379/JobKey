// Variables globales
let currentPage = 1;
let pageSize = 10;
let totalRecords = 0;
let institucionesData = [];

// Datos mock
const institucionesMock = [
    { id: 1, nombre: "Universidad Central", nit: "123456789-0", tipo: "UNIVERSIDAD", estado: "ACTIVA", logoUrl: "", email: "info@ucentral.edu", telefono: "6012345678", direccion: "Calle 10 #5-20", descripcion: "La mejor universidad del país", sitioWeb: "https://www.ucentral.edu", fechaRegistro: "2024-01-15" },
    { id: 2, nombre: "Empresa Tech S.A.S", nit: "987654321-0", tipo: "EMPRESA", estado: "ACTIVA", logoUrl: "", email: "contacto@techsas.com", telefono: "6051234567", direccion: "Calle 50 #15-30", descripcion: "Desarrollo de software", sitioWeb: "https://www.techsas.com", fechaRegistro: "2024-01-20" },
    { id: 3, nombre: "Instituto Nacional", nit: "456123789-0", tipo: "INSTITUTO", estado: "INACTIVA", logoUrl: "", email: "info@inf.edu.co", telefono: "6021234567", direccion: "Carrera 20 #8-15", descripcion: "Formación técnica", sitioWeb: "https://www.inf.edu.co", fechaRegistro: "2024-02-01" },
    { id: 4, nombre: "Fundación Ayuda Social", nit: "789456123-0", tipo: "ONG", estado: "PENDIENTE", logoUrl: "", email: "info@ayudasocial.org", telefono: "6041234567", direccion: "Calle 80 #45-60", descripcion: "Ayuda social", sitioWeb: "https://www.ayudasocial.org", fechaRegistro: "2024-02-10" },
    { id: 5, nombre: "Universidad del Norte", nit: "321654987-0", tipo: "UNIVERSIDAD", estado: "ACTIVA", logoUrl: "", email: "info@uninorte.edu", telefono: "6059876543", direccion: "Calle 30 #20-10", descripcion: "Educación de calidad", sitioWeb: "https://www.uninorte.edu", fechaRegistro: "2024-02-15" }
];

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    loadInstituciones();

    document.getElementById('searchInput').addEventListener('input', () => { currentPage = 1; loadInstituciones(); });
    document.getElementById('tipoFilter').addEventListener('change', () => { currentPage = 1; loadInstituciones(); });
    document.getElementById('estadoFilter').addEventListener('change', () => { currentPage = 1; loadInstituciones(); });
});

function loadInstituciones() {
    const search = document.getElementById('searchInput').value.toLowerCase();
    const tipoFilter = document.getElementById('tipoFilter').value;
    const estadoFilter = document.getElementById('estadoFilter').value;

    let filtered = institucionesMock.filter(i => {
        return (!search || i.nombre.toLowerCase().includes(search) ||
               i.nit.includes(search) ||
               i.email.toLowerCase().includes(search)) &&
               (!tipoFilter || i.tipo === tipoFilter) &&
               (!estadoFilter || i.estado === estadoFilter);
    });

    institucionesData = filtered;
    totalRecords = filtered.length;
    const start = (currentPage - 1) * pageSize;
    const paginated = filtered.slice(start, start + pageSize);

    renderTable(paginated);
    renderPagination();
}

function renderTable(instituciones) {
    const tbody = document.getElementById('institucionesBody');
    tbody.innerHTML = '';

    if (instituciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="9" style="text-align: center; padding: 2rem;">No se encontraron instituciones</td></tr>';
        document.getElementById('paginationInfo').innerHTML = 'Mostrando 0 de 0 registros';
        return;
    }

    instituciones.forEach(i => {
        const row = tbody.insertRow();
        row.insertCell(0).innerHTML = i.id;
        row.insertCell(1).innerHTML = `<div class="logo-placeholder">${i.nombre.charAt(0).toUpperCase()}</div>`;
        row.insertCell(2).innerHTML = `<strong>${i.nombre}</strong>`;
        row.insertCell(3).innerHTML = i.nit;
        row.insertCell(4).innerHTML = `<span class="badge badge-${getTipoClass(i.tipo)}">${getTipoNombre(i.tipo)}</span>`;
        row.insertCell(5).innerHTML = i.email;
        row.insertCell(6).innerHTML = i.telefono || '-';
        row.insertCell(7).innerHTML = `<span class="badge badge-${getEstadoClass(i.estado)}">${i.estado}</span>`;
        row.insertCell(8).innerHTML = `
            <button class="btn-icon btn-edit" onclick="editInstitucion(${i.id})"><i class="fas fa-edit"></i></button>
            <button class="btn-icon btn-delete" onclick="deleteInstitucion(${i.id})"><i class="fas fa-trash"></i></button>
        `;
    });

    const start = (currentPage - 1) * pageSize + 1;
    const end = Math.min(currentPage * pageSize, totalRecords);
    document.getElementById('paginationInfo').innerHTML = `Mostrando ${start}-${end} de ${totalRecords} registros`;
}

function getTipoClass(tipo) {
    const clases = { UNIVERSIDAD: 'universidad', EMPRESA: 'empresa', INSTITUTO: 'instituto', ONG: 'ong' };
    return clases[tipo] || 'universidad';
}

function getTipoNombre(tipo) {
    const nombres = { UNIVERSIDAD: 'Universidad', EMPRESA: 'Empresa', INSTITUTO: 'Instituto', ONG: 'ONG' };
    return nombres[tipo] || tipo;
}

function getEstadoClass(estado) {
    const clases = { ACTIVA: 'activa', INACTIVA: 'inactiva', PENDIENTE: 'pendiente' };
    return clases[estado] || 'pendiente';
}

function renderPagination() {
    const totalPages = Math.ceil(totalRecords / pageSize);
    const paginationDiv = document.getElementById('pagination');
    paginationDiv.innerHTML = '';

    if (totalPages <= 1) return;

    // Anterior
    const prevBtn = document.createElement('button');
    prevBtn.className = `page-btn ${currentPage === 1 ? 'disabled' : ''}`;
    prevBtn.innerHTML = 'Anterior';
    prevBtn.onclick = () => { if (currentPage > 1) changePage(currentPage - 1); };
    paginationDiv.appendChild(prevBtn);

    // Números
    for (let i = 1; i <= Math.min(totalPages, 5); i++) {
        const pageBtn = document.createElement('button');
        pageBtn.className = `page-btn ${i === currentPage ? 'active' : ''}`;
        pageBtn.innerHTML = i;
        pageBtn.onclick = () => changePage(i);
        paginationDiv.appendChild(pageBtn);
    }

    // Siguiente
    const nextBtn = document.createElement('button');
    nextBtn.className = `page-btn ${currentPage === totalPages ? 'disabled' : ''}`;
    nextBtn.innerHTML = 'Siguiente';
    nextBtn.onclick = () => { if (currentPage < totalPages) changePage(currentPage + 1); };
    paginationDiv.appendChild(nextBtn);
}

function changePage(page) {
    currentPage = page;
    loadInstituciones();
}

function resetFilters() {
    document.getElementById('searchInput').value = '';
    document.getElementById('tipoFilter').value = '';
    document.getElementById('estadoFilter').value = '';
    currentPage = 1;
    loadInstituciones();
}

function openModal() {
    document.getElementById('institucionModal').classList.add('active');
}

function closeModal() {
    document.getElementById('institucionModal').classList.remove('active');
    document.getElementById('institucionForm').reset();
    document.getElementById('id').value = '';
}

function openCreateModal() {
    document.getElementById('modalTitle').innerHTML = '<i class="fas fa-building"></i> Nueva Institución';
    openModal();
}

function editInstitucion(id) {
    const institucion = institucionesData.find(i => i.id === id);
    if (!institucion) return;

    document.getElementById('modalTitle').innerHTML = '<i class="fas fa-edit"></i> Editar Institución';
    document.getElementById('id').value = institucion.id;
    document.getElementById('nombre').value = institucion.nombre;
    document.getElementById('nit').value = institucion.nit;
    document.getElementById('tipo').value = institucion.tipo;
    document.getElementById('estado').value = institucion.estado;
    document.getElementById('logoUrl').value = institucion.logoUrl || '';
    document.getElementById('email').value = institucion.email;
    document.getElementById('telefono').value = institucion.telefono || '';
    document.getElementById('direccion').value = institucion.direccion || '';
    document.getElementById('descripcion').value = institucion.descripcion || '';
    document.getElementById('sitioWeb').value = institucion.sitioWeb || '';
    document.getElementById('fechaRegistro').value = institucion.fechaRegistro || '';

    openModal();
}

function saveInstitucion() {
    const id = document.getElementById('id').value;
    const data = {
        nombre: document.getElementById('nombre').value,
        nit: document.getElementById('nit').value,
        tipo: document.getElementById('tipo').value,
        estado: document.getElementById('estado').value,
        logoUrl: document.getElementById('logoUrl').value,
        email: document.getElementById('email').value,
        telefono: document.getElementById('telefono').value,
        direccion: document.getElementById('direccion').value,
        descripcion: document.getElementById('descripcion').value,
        sitioWeb: document.getElementById('sitioWeb').value,
        fechaRegistro: document.getElementById('fechaRegistro').value || new Date().toISOString().split('T')[0]
    };

    if (!data.nombre || !data.nit || !data.tipo || !data.estado || !data.email) {
        Swal.fire('Error', 'Complete todos los campos requeridos', 'error');
        return;
    }

    if (id) {
        const index = institucionesMock.findIndex(i => i.id == id);
        if (index !== -1) {
            institucionesMock[index] = { ...institucionesMock[index], ...data };
            Swal.fire('Éxito', 'Institución actualizada correctamente', 'success');
        }
    } else {
        const newId = Math.max(...institucionesMock.map(i => i.id), 0) + 1;
        institucionesMock.push({ id: newId, ...data });
        Swal.fire('Éxito', 'Institución creada correctamente', 'success');
    }

    closeModal();
    loadInstituciones();
}

function deleteInstitucion(id) {
    const institucion = institucionesData.find(i => i.id === id);

    Swal.fire({
        title: '¿Eliminar institución?',
        html: `¿Está seguro de eliminar <strong>${institucion.nombre}</strong>?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#C0392B',
        cancelButtonColor: '#1B2A4A',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            const index = institucionesMock.findIndex(i => i.id === id);
            if (index !== -1) {
                institucionesMock.splice(index, 1);
                loadInstituciones();
                Swal.fire('Eliminada', 'Institución eliminada correctamente', 'success');
            }
        }
    });
}

// Cerrar modal con ESC
document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
        closeModal();
    }
});

// Cerrar modal clickeando fuera
document.getElementById('institucionModal').addEventListener('click', function(e) {
    if (e.target === this) {
        closeModal();
    }
});