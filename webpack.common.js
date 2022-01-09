const path = require('path')
const {TsconfigPathsPlugin}  = require('tsconfig-paths-webpack-plugin');
const {WebpackManifestPlugin} = require("webpack-manifest-plugin");
const appPath = path.join(__dirname,'src','main','react','app')
module.exports = {
    entry: {
        'counter': path.join(appPath,'counter-app.tsx')
    },
    output: {
        path: path.join(__dirname,'target','classes','static','resources'),
        filename: '[name].bundle.js',
        publicPath: "/resources/"
    },
    resolve: {
        extensions: ['.tsx','.ts','.js','.jsx','.css','.scss', '.json'],
        plugins: [
            new TsconfigPathsPlugin()
        ]
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                exclude: /node_modules/,
                loader: "ts-loader",
                options: {
                    transpileOnly: true
                }
            },
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: "babel-loader",
            },
            {
                test:/\.(css|scss)$/, use: ["style-loader", "css-loader","sass-loader"]
            },
            {
                test: /\.(jpg|jpeg|png|gif|mp3|svg)$/,
                use: ["file-loader"],
            },
        ]
    },
    plugins: [
        new WebpackManifestPlugin({})
    ]
}
