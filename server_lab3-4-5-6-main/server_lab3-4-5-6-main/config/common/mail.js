var nodemailer = require("nodemailer");
const transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: "hieunvph38422@fpt.edu.vn",
        pass: "gtaz orwz owvr qhgm"
    }
});
module.exports = transporter 