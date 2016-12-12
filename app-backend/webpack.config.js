var webpack = require('webpack');

module.exports = require('./scalajs.webpack.config');

// make sure all deps are for nodejs
module.exports.target = "node"
