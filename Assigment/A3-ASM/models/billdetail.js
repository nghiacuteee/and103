const mongoose = require("mongoose");
const Scheme = mongoose.Schema;

const BillDetail = new Scheme(
  {
    billid: { type: mongoose.Types.ObjectId, ref: "bill", require: true },
    productid: { type: mongoose.Types.ObjectId, ref: "product", require: true },
    quantity: { type: Number, default: 1 },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("billdetail", BillDetail);
