const mongoose = require('mongoose')
const Scheme = mongoose.Schema

const Bill = new Scheme(
    {
        email:{type: String, require:true}
    },
    {
        timestamps:true
    }
)
module.exports = mongoose.model('bill', Bill)