module.exports = function(app) {
//API Routes
app.get('/', function(req, res) {
    res.send('Hello World');
});
}