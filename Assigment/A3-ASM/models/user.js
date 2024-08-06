const mongoose = require("mongoose");
const Scheme = mongoose.Schema;

const Users = new Scheme(
  {
    email: { type: String, unique: true, required: true },
    name: { type: String, required: true },
    pass: { type: String, required: true },
    avatar: {
      type: String,
      default:
        "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png",
    },
    available: { type: Boolean, default: true },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("account", Users);
