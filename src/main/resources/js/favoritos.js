/**
 * Favoritos.js:
 * Funciones para manejar los favoritos creados por el usuario sin registro:
 */

document.addEventListener("DOMContentLoaded", function () {
    const favButtons = document.querySelectorAll(".fav-btn");
    const favList = document.getElementById("fav-list");

    // Cargar favoritos desde Local Storage
    const favoritos = JSON.parse(localStorage.getItem("favoritos")) || [];

    // Renderizar favoritos
    const renderFavoritos = () => {
        favList.innerHTML = favoritos
            .map(fav => `<li>${fav.title} <button onclick="removeFavorito(${fav.id})">Eliminar</button></li>`)
            .join("");
    };

    // Añadir favorito
    const addFavorito = (id, title) => {
        if (!favoritos.some(fav => fav.id === id)) {
            favoritos.push({ id, title });
            localStorage.setItem("favoritos", JSON.stringify(favoritos));
            renderFavoritos();
        }
    };

    // Quitar favorito
    const removeFavorito = id => {
        const index = favoritos.findIndex(fav => fav.id === id);
        if (index > -1) {
            favoritos.splice(index, 1);
            localStorage.setItem("favoritos", JSON.stringify(favoritos));
            renderFavoritos();
        }
    };

    // Asociar eventos
    favButtons.forEach(button => {
        button.addEventListener("click", function () {
            const id = this.dataset.id;
            const title = this.dataset.title;
            if (this.textContent.includes("Añadir")) {
                addFavorito(id, title);
                this.textContent = "Quitar de Favoritos";
            } else {
                removeFavorito(id);
                this.textContent = "Añadir a Favoritos";
            }
        });
    });

    renderFavoritos();
});