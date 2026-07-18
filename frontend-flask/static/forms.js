function formatarMoeda(valor) {
    const somenteNumeros = valor.replace(/\D/g, "");

    if (!somenteNumeros) {
        return "";
    }

    const numero = Number(somenteNumeros) / 100;

    return numero.toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

function moedaParaDecimal(valor) {
    return valor
        .replace(/\./g, "")
        .replace(",", ".");
}

document.querySelectorAll(".money-input").forEach((input) => {
    input.addEventListener("input", () => {
        input.value = formatarMoeda(input.value);
    });
});

document.querySelectorAll("form").forEach((form) => {
    form.addEventListener("submit", () => {
        form.querySelectorAll(".money-input").forEach((input) => {
            input.value = moedaParaDecimal(input.value);
        });
    });
});