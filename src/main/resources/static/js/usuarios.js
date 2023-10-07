// Call the dataTables jQuery plugin
$(document).ready(function () {
    cargarUsuarios();
    $('#usuarios').DataTable();
    actualizarEmailDelUsuario();
});

function  actualizarEmailDelUsuario(){
    document.getElementById("txtemail-usuario").outerHTML=localStorage.email;
}//Fin actualizarEmailDelUsuario

async function cargarUsuarios() {
    const request = await fetch('api/usuarios', {//await: Esperar el resultado
        method: 'GET',
        headers: getHeaders()
    });
    //Se convierte el resultado en JSON
    const usuarios = await request.json(); // trae un listado de usuarios

    let listadoHTML = '';
    for (let usuario of usuarios) {
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;

        let usuariohtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
            + usuario.email + '</td><td>' + telefonoTexto
            + '</td><td>' + botonEliminar + '</td></tr>';
        listadoHTML += usuariohtml;
    }//Fin for

    document.querySelector('#usuarios tbody').outerHTML = listadoHTML;
}//Fin cargarUsuarios

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token

    };
}//Fin getHeaders

async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar este usuario?')) {
        return; // True or False
    }//Fin if
    const request = await fetch('api/usuarios/' + id, {//await: Esperar el resultado
        method: 'DELETE',
        headers: getHeaders()
    });
    location.reload()
}//Fin eliminarUsuario