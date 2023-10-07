// Call the dataTables jQuery plugin
$(document).ready(function () {
// TODO CODE ALL HERE
});

async function registrarUsuario() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if (repetirPassword != datos.password) {
        alert('La contraseña que escribiste es diferente.');
        return; //Corta la función
    }
    // el fetch se coloca tal cual en el AuthController de UsuarioController
    const request = await fetch('api/usuarios', {//await: Esperar el resultado
        method: 'POST',
        headers: {
            //Indica que usara Json
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)// Llama a la funcion y agarra cualquier objeto a string de JSON
    });
    alert("La cuenta fue creada con exito!");
    window.location.href = 'login.html'

}//Fin cargarUsuarios
