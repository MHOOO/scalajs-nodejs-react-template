var webpack = require('webpack');
var path = require('path');
var fs = require('fs');

module.exports = require('./scalajs.webpack.config');

// module.exports.externals = {"react": "React",
//                             "react-dom": "ReactDOM"};

module.exports.plugins = [
    // new webpack.IgnorePlugin(/react/),
    // new webpack.IgnorePlugin(/react-dom/)
    new webpack.ProvidePlugin({
        React: "react", react: "react", "window.react": "react", "window.React": "react",
        ReactDOM: "react-dom", "window.ReactDOM" : "react-dom"
    })
];

// module.loaders = [{test: require.resolve("react"), loader: "expose-loader?React"},
//                   {test: require.resolve("react-dom"), loader: "expose-loader?ReactDOM"}]
