function openModal() {
    document.getElementById('modal').classList.add('active');
    document.getElementById('modalTitle').innerHTML = 'Nueva Institución';
    document.getElementById('institucionForm').reset();
    document.getElementById('id').value = '';
}

function closeModal() {
    document.getElementById('modal').classList.remove('active');
}

function edit(id) {
    fetch('/api/instituciones/' + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('modalTitle').innerHTML = 'Editar Institución';
            document.getElementById('id').value = data.idInstitucion;
            document.getElementById('nombre').value = data.nombreInstitucion || '';
            document.getElementById('telefono').value = data.telefono || '';
            document.getElementById('tipo').value = data.tipo || 'UNIVERSIDAD';
            document.getElementById('logo').value = data.logo || '';
            document.getElementById('descripcion').value = data.descripcion || '';
            document.getElementById('departamentoId').value = data.idDepartamento || '';
            openModal();
        })
        .catch(error => {
            alert('Error al cargar los datos');
        });
}

function save() {
    const id = document.getElementById('id').value;
    const data = {
        nombreInstitucion: document.getElementById('nombre').value,
        telefono: document.getElementById('telefono').value,
        tipo: document.getElementById('tipo').value,
        logo: document.getElementById('logo').value,
        descripcion: document.getElementById('descripcion').value,
        departamentoId: document.getElementById('departamentoId').value ? parseInt(document.getElementById('departamentoId').value) : null
    };

    if (!data.nombreInstitucion || !data.tipo) {
        alert('Complete los campos obligatorios');
        return;
    }

    const url = id ? '/api/instituciones/' + id : '/api/instituciones';
    const method = id ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('Error al guardar');
        }
    })
    .catch(error => {
        alert('Error de conexión');
    });
}