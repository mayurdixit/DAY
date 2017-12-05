const PROXY_CONFIG = [
    {
        context: [
            "/authorize"
        ],
        target: "http://localhost:8888",
        secure: false
    },
    {
        context: [
            "/internal"
        ],
        target: "http://localhost:8888",
        secure: false
    }
]

module.exports = PROXY_CONFIG;