// ── usuarios.js ──────────────────────────────────────────
// Lógica para la vista de gestión de usuarios:
//   - Búsqueda en tiempo real por email
//   - Filtro por rol (pills)
//   - Modales: Ver detalle, Editar, Eliminar

document.addEventListener('DOMContentLoaded', () => {

    // ── Estado del filtro activo ─────────────────────────
    let rolActivo = 'todos';

    // ── Búsqueda por email ───────────────────────────────
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', () => {
            aplicarFiltros(searchInput.value.trim().toLowerCase(), rolActivo);
        });
    }

    // ── Filtro por rol (pills) ───────────────────────────
    document.querySelectorAll('.pill').forEach(pill => {
        pill.addEventListener('click', () => {
            document.querySelectorAll('.pill').forEach(p => p.classList.remove('active'));
            pill.classList.add('active');
            rolActivo = pill.dataset.rol;
            const texto = searchInput ? searchInput.value.trim().toLowerCase() : '';
            aplicarFiltros(texto, rolActivo);
        });
    });

    // ── Botones de la tabla (delegación de eventos) ──────
    const tbody = document.querySelector('#tablaUsuarios tbody');
    if (tbody) {
        tbody.addEventListener('click', e => {
            const fila = e.target.closest('tr[data-id]');
            if (!fila) return;

            const datos = {
                id       : fila.dataset.id,
                email    : fila.dataset.email,
                rol      : fila.dataset.rol,
                acceso   : fila.dataset.acceso,
                registro : fila.dataset.registro,
            };

            if (e.target.closest('.btn-ver'))      abrirDetalle(datos);
            if (e.target.closest('.btn-editar'))   abrirEditar(datos);
            if (e.target.closest('.btn-eliminar')) abrirEliminar(datos);
        });
    }

    // ── Cerrar modales con botones data-close ────────────
    document.querySelectorAll('[data-close]').forEach(btn => {
        btn.addEventListener('click', () => cerrarModal(btn.dataset.close));
    });

    // ── Cerrar modal al hacer click en el overlay ────────
    document.querySelectorAll('.modal-overlay').forEach(overlay => {
        overlay.addEventListener('click', e => {
            if (e.target === overlay) cerrarModal(overlay.id);
        });
    });

    // ── Cerrar modal con Escape ──────────────────────────
    document.addEventListener('keydown', e => {
        if (e.key === 'Escape') {
            document.querySelectorAll('.modal-overlay.open')
                    .forEach(m => cerrarModal(m.id));
        }
    });

    // ── Auto-ocultar alertas después de 4s ──────────────
    document.querySelectorAll('.alert').forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        }, 4000);
    });

});

// ── Función principal de filtrado ────────────────────────
function aplicarFiltros(texto, rol) {
    const filas = document.querySelectorAll('#tablaUsuarios tbody tr[data-email]');
    let visibles = 0;

    filas.forEach(fila => {
        const emailMatch = fila.dataset.email.toLowerCase().includes(texto);
        const rolMatch   = rol === 'todos' || fila.dataset.rol === rol;

        if (emailMatch && rolMatch) {
            fila.style.display = '';
            visibles++;
        } else {
            fila.style.display = 'none';
        }
    });

    const noResults = document.getElementById('noResults');
    if (noResults) noResults.style.display = visibles === 0 ? 'block' : 'none';
}

// ── Modal: Ver detalle ───────────────────────────────────
function abrirDetalle({ id, email, rol, acceso, registro }) {
    document.getElementById('det-id').textContent       = id;
    document.getElementById('det-email').textContent    = email;
    document.getElementById('det-rol').textContent      = rol;
    document.getElementById('det-acceso').textContent   = acceso;
    document.getElementById('det-registro').textContent = registro;
    abrirModal('modalDetalle');
}

// ── Modal: Editar ────────────────────────────────────────
function abrirEditar({ id, email, rol }) {
    document.getElementById('edit-id').value    = id;
    document.getElementById('edit-email').value = email;
    document.getElementById('edit-rol').value   = rol;
    abrirModal('modalEditar');
}

// ── Modal: Eliminar ──────────────────────────────────────
function abrirEliminar({ id, email }) {
    document.getElementById('del-id').value          = id;
    document.getElementById('del-email').textContent = email;
    abrirModal('modalEliminar');
}

// ── Helpers de modal ─────────────────────────────────────
function abrirModal(id) {
    const modal = document.getElementById(id);
    if (modal) {
        modal.classList.add('open');
        document.body.style.overflow = 'hidden';
    }
}

function cerrarModal(id) {
    const modal = document.getElementById(id);
    if (modal) {
        modal.classList.remove('open');
        document.body.style.overflow = '';
    }
}