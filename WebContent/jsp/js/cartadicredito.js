if (window.parent && window.parent.parent) {
    window.parent.parent.postMessage(["resultsFrame", {
        height: document.body.getBoundingClientRect().height,
        slug: "zbxvcwjm"
    }], "*")
}

// always overwrite window.name, in case users try to set it manually
window.name = "result"


$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})
