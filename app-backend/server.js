/**
 * Bootstrap code for backend
 */
(function () {
    require("./target/scala-2.11/app-backend-fastopt.js");
    const facade = app.main();
    facade.startServer({
        "__dirname": __dirname,
        "__filename": __filename,
        "exports": exports,
        "module": module,
        "require": require
    });
})();
