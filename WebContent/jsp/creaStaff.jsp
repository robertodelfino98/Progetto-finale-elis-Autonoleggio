<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Crea Utente Staff</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
            crossorigin="anonymous"
    />
</head>
<body>
<menu></menu>
<div class="container-md">
    <div class="my-container">
        <div class="row">
            <div class="col-12" class="registrati">
                <h1>Crea Utente Staff</h1>
            </div>
            <div class="row">
                <div class="col-12">
                    <h3 class="titolo">Informazioni Personali</h3>
                </div>
            </div>
        </div>
    <form action="aggiungistaff" method="POST">
        <div class="row">
            <div class="col-3">
                <h4>Nome</h4>
            </div>
            <div class="col-9">
                <input
                        type="text"
                        class="form-control"
                        placeholder="Last name"
                        name="nome"
                        required
                />
            </div>
        </div>
        <div class="row">
            <div class="col-3">
                <h4>Cognome</h4>
            </div>
            <div class="col-9">
                <input
                        type="text"
                        class="form-control"
                        placeholder="Last name"
                        name="cognome"
                        required
                />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-3">
            <h4>Email</h4>
        </div>
        <div class="col-9">
            <input
                    type="email"
                    class="form-control"
                    placeholder="inserisci email"
                    name="email"
                    required
            />
        </div>
    </div>

    <div class="row" id="row-invia">
        <div class="col-3"></div>
        <div class="col-1"></div>
        <div class="col-7">
            <input type="submit" class="form-control" placeholder="Submit"/>
        </div>
        <div class="col-1"></div>
    </div>
    </form>
</div>
<script
        scr="https://unpkg.com/@popperjs/core@2.4.0/dist/umd/popper.min.js"
></script>

<script scr="js/bootstrap.js"></script>
</body>
</html>
