var webpack = require('webpack');
var path = require('path');
var fs = require('fs');

module.exports = require('./scalajs.webpack.config');

module.exports.externals = {"react": "React",
                            "react-dom": "ReactDOM"};

module.exports.plugins = [
    // new webpack.IgnorePlugin(/react/),
    // new webpack.IgnorePlugin(/react-dom/)
    new webpack.ProvidePlugin({
        React: "React", react: "React", "window.react": "React", "window.React": "React"
    })
];
