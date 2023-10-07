// Call the dataTables jQuery plugin
$(document).ready(function () {
// TODO CODE ALL HERE
});

async function iniciarSesion() {
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    //Llama al servidor: > el fetch se coloca tal cuual en el AuthController de login
    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            //Indica que usara Json
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)// Llama a la función y agarra cualquier objeto a string de JSON
    });
    const respuesta = await request.text(); // trae un listado de usuarios

    if (respuesta != 'FAIL') {
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'
    } else {
        alert("Usuario o password incorrecto, digite correctamente!!!");
    }//Fin else

}//Fin cargarUsuarios
