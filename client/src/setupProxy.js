const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        createProxyMiddleware(['/api', '/ws'], {
            target: process.env.back_url,
            logLevel: 'debug',
            ws: true
        })
    );
};
