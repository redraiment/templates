const parser = require('body-parser');

module.exports = (app) => {
    app.use(parser.json());

    app.get('/api/session', (request, response) => {
	response.json({
	    name: 'Joe'
	});
    });
};
