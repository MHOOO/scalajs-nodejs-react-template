/**
 * Bootstrap code for backend
 */
(function () {
    // require("./target/scala-2.11/scalajsbundler-deps.js");
    // require("./target/scala-2.11/scalajsbundler-entry-point.js");
    // var app = require("./target/scala-2.11/app-backend-fastopt.js").__ScalaJSExportsNamespace.app;
    require("./target/scala-2.11/app-backend-fastopt-bundle.js");
    const facade = app.BackendApp();
    facade.startServer({
        "__dirname": __dirname,
        "__filename": __filename,
        "exports": exports,
        "module": module,
        "require": require
    });
})();
