var webpack = require('webpack');
var path = require('path');
var fs = require('fs');

module.exports = require('./scalajs.webpack.config');

// make sure all deps are for nodejs
module.exports.target = "node"

// note the path.resolve(__dirname, ...) part
// without it, eslint-import-resolver-webpack fails
// since eslint might be invoked with different cwd
var nodeModules = {};
fs.readdirSync(path.resolve(__dirname, 'node_modules'))
    .filter(x => ['.bin'].indexOf(x) === -1)
    .forEach(mod => { nodeModules[mod] = `commonjs ${mod}`; });

module.exports.externals = nodeModules
