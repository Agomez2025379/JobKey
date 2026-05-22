// admins.js

// ── Búsqueda en tabla ────────────────────────────────────
function filtrar() {
    const q = document.getElementById('search')?.value.toLowerCase() ?? '';
    const filas = document.querySelectorAll('#tabla tbody tr[data-nombre]');
    let visibles = 0;

    filas.forEach(fila => {
        const nombre = fila.dataset.nombre?.toLowerCase() ?? '';
        const email  = fila.dataset.email?.toLowerCase()  ?? '';
        const match  = nombre.includes(q) || email.includes(q);
        fila.style.display = match ? '' : 'none';
        if (match) visibles++;
    });

    const emptyRow = document.getElementById('empty-row');
    if (emptyRow) emptyRow.style.display = visibles === 0 ? '' : 'none';
}

// ── Toggle mostrar/ocultar contraseña ────────────────────
function togglePass(inputId, btn) {
    const input = document.getElementById(inputId);
    const icon  = btn.querySelector('i');
    if (input.type === 'password') {
        input.type = 'text';
        icon.classList.replace('ti-eye', 'ti-eye-off');
    } else {
        input.type = 'password';
        icon.classList.replace('ti-eye-off', 'ti-eye');
    }
}

// ── Validación formulario CREAR ──────────────────────────
function validarCrear() {
    let ok = true;

    const nombre = document.getElementById('nombre');
    const email  = document.getElementById('email');
    const pass   = document.getElementById('password');
    const pass2  = document.getElementById('password2');

    limpiarErrores();

    if (!nombre.value.trim()) {
        mostrarError('err-nombre', 'El nombre es obligatorio.');
        ok = false;
    }

    if (!email.value.trim() || !email.value.includes('@')) {
        mostrarError('err-email', 'Ingresa un email válido.');
        ok = false;
    }

    if (pass.value.length < 8) {
        mostrarError('err-pass', 'La contraseña debe tener al menos 8 caracteres.');
        ok = false;
    }

    if (pass.value !== pass2.value) {
        mostrarError('err-pass2', 'Las contraseñas no coinciden.');
        ok = false;
    }

    return ok;
}

// ── Validación formulario EDITAR ─────────────────────────
function validarEditar() {
    let ok = true;

    const nombre = document.getElementById('nombre');
    const email  = document.getElementById('email');

    limpiarErrores();

    if (!nombre.value.trim()) {
        mostrarError('err-nombre', 'El nombre es obligatorio.');
        ok = false;
    }

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