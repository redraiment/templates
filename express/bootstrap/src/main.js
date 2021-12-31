const parser = require('body-parser');
const app = require('express')();
app.use(parser.json());

app.get('/', (request, response) => {
    response.json({
	message: 'Hello Express!'
    });
});

app.listen(3000, () => {
    console.log('http server started @ 3000');
});
