var webpack = require('webpack');
var path = require('path');
var fs = require('fs');

module.exports = require('./scalajs.webpack.config');

//module.exports.externals = [{}];

module.exports.plugins = [
    /// This would make sure react is available as "React" in every module
    // new webpack.ProvidePlugin({
    //     React: "react",
    //     "window.React": "react",
    //     ReactDOM: "react-dom",
    //     "window.ReactDOM": "react-dom"
    // })
];

module.exports.module.loaders = [
    // Make sure that react is available to e.g. the scalajs-react facade
    {test: require.resolve("react"), loader: "expose?React"},
    {test: require.resolve("react-dom"), loader: "expose?ReactDOM"},

    {
        test: /\.less$/,
        loader: "style-loader!css-loader!less-loader"
    }
]
