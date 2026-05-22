// usuarios.js

// ── Búsqueda en tabla ────────────────────────────────────
function filtrar() {
    const q = document.getElementById('search')?.value.toLowerCase() ?? '';
    const filas = document.querySelectorAll('#tabla tbody tr[data-email]');
    let visibles = 0;

    filas.forEach(fila => {
        const email = fila.dataset.email?.toLowerCase() ?? '';
        const rol   = fila.dataset.rol?.toLowerCase()   ?? '';
        const match = email.includes(q) || rol.includes(q);
        fila.style.display = match ? '' : 'none';
        if (match) visibles++;
    });

    const emptyRow = document.getElementById('empty-row');
    if (emptyRow) emptyRow.style.display = visibles === 0 ? '' : 'none';
}

// ── Validación formulario EDITAR ─────────────────────────
function validarEditar() {
    let ok = true;
    limpiarErrores();

    const email = document.getElementById('email');

    if (!email.value.trim() || !email.value.includes('@')) {
        mostrarError('err-email', 'Ingresa un email válido.');
        ok = false;
    }

    return ok;
}

// ── Helpers ──────────────────────────────────────────────
function mostrarError(id, msg) {
    const el = document.getElementById(id);
    if (el) { el.textContent = msg; el.style.display = 'block'; }
}

function limpiarErrores() {
    document.querySelectorAll('.field-error').forEach(el => {
        el.textContent = '';
        el.style.display = 'none';
    });
}