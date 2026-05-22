// admins.js

function validarCrear() {
    let isValid = true;

    // Limpiar errores anteriores
    document.getElementById('err-nombre').innerText = '';
    document.getElementById('err-email').innerText = '';
    document.getElementById('err-pass').innerText = '';
    document.getElementById('err-pass2').innerText = '';

    // Remover clase error de los inputs
    document.getElementById('nombre').classList.remove('error');
    document.getElementById('email').classList.remove('error');
    document.getElementById('password').classList.remove('error');
    document.getElementById('password2').classList.remove('error');

    // Validar nombre
    const nombre = document.getElementById('nombre').value.trim();
    if (!nombre) {
        document.getElementById('err-nombre').innerText = 'El nombre es obligatorio';
        document.getElementById('nombre').classList.add('error');
        isValid = false;
    }

    // Validar email
    const email = document.getElementById('email').value.trim();
    if (!email) {
        document.getElementById('err-email').innerText = 'El email es obligatorio';
        document.getElementById('email').classList.add('error');
        isValid = false;
    } else if (!email.includes('@') || !email.includes('.')) {
        document.getElementById('err-email').innerText = 'Ingresa un email válido';
        document.getElementById('email').classList.add('error');
        isValid = false;
    }

    // Validar contraseña
    const password = document.getElementById('password').value;
    if (!password) {
        document.getElementById('err-pass').innerText = 'La contraseña es obligatoria';
        document.getElementById('password').classList.add('error');
        isValid = false;
    } else if (password.length < 8) {
        document.getElementById('err-pass').innerText = 'La contraseña debe tener al menos 8 caracteres';
        document.getElementById('password').classList.add('error');
        isValid = false;
    }

    // Validar confirmación de contraseña
    const password2 = document.getElementById('password2').value;
    if (!password2) {
        document.getElementById('err-pass2').innerText = 'Confirma tu contraseña';
        document.getElementById('password2').classList.add('error');
        isValid = false;
    } else if (password !== password2) {
        document.getElementById('err-pass2').innerText = '✗ Las contraseñas no coinciden';
        document.getElementById('password2').classList.add('error');
        isValid = false;
    }

    return isValid;
}

function validarEditar() {
    let isValid = true;

    // Limpiar errores anteriores
    document.getElementById('err-nombre').innerText = '';
    document.getElementById('err-email').innerText = '';

    // Remover clase error de los inputs
    document.getElementById('nombre').classList.remove('error');
    document.getElementById('email').classList.remove('error');

    // Validar nombre
    const nombre = document.getElementById('nombre').value.trim();
    if (!nombre) {
        document.getElementById('err-nombre').innerText = 'El nombre es obligatorio';
        document.getElementById('nombre').classList.add('error');
        isValid = false;
    }

    // Validar email
    const email = document.getElementById('email').value.trim();
    if (!email) {
        document.getElementById('err-email').innerText = 'El email es obligatorio';
        document.getElementById('email').classList.add('error');
        isValid = false;
    } else if (!email.includes('@') || !email.includes('.')) {
        document.getElementById('err-email').innerText = 'Ingresa un email válido';
        document.getElementById('email').classList.add('error');
        isValid = false;
    }

    return isValid;
}

function togglePass(fieldId, button) {
    const input = document.getElementById(fieldId);
    const icon = button.querySelector('i');

    if (input.type === 'password') {
        input.type = 'text';
        icon.classList.remove('ti-eye');
        icon.classList.add('ti-eye-off');
    } else {
        input.type = 'password';
        icon.classList.remove('ti-eye-off');
        icon.classList.add('ti-eye');
    }
}