var express = require("express");
var router = express.Router();

const Car = require("../model/Car");

router.get("/", (rq, rs) => {
  rs.send("Vao API mobile");
});

router.get("/get-list-car", async (rq, rs) => {
  try {
    const data = await Car.find();

    rs.send(data);
  } catch (error) {
    console.log(error);
  }
});

router.post("/post-car", async (rq, rs) => {
  try {
    const data = rq.body;
    const newCar = new Car({
      ten: data.ten,
      namSX: data.namSX,
      hang: data.hang,
      gia: data.gia,
    });
    const reslut = await newCar.save();

    if (reslut) {
      rs.json({
        status: 200,
        messenger: "Create car successfully",
        data: reslut,
      });
    } else {
      rs.json({
        status: 400,
        messenger: "Erro",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.delete("/delete-car-by-id/:id", async (rq, rs) => {
  try {
    const { id } = rq.params;
    const result = await Car.findByIdAndDelete(id);

    if (result) {
      rs.json({
        status: 200,
        messenger: "Delete car successfully",
        data: result,
      });
    } else {
      rs.json({
        status: 400,
        messenger: "ERRO",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.put("/update-car-by-id/:id", async (rq, rs) => {
  try {
    const { id } = rq.params;
    const data = rq.body;

    const updateCar = await Car.findById(id);

    let reslut = null;
    if (updateCar) {
      updateCar.ten = data.ten ?? updateShoe.ten;
      updateCar.namSX = data.namSX ?? updateShoe.namSX;
      updateCar.hang = data.hang ?? updateShoe.hang;
      updateCar.gia = data.gia ?? updateShoe.gia;

      reslut = await updateCar.save();
    }

    if (reslut) {
      rs.json({
        status: 200,
        messenger: "Update car successfully",
        data: reslut,
      });
    } else {
      rs.json({
        status: 400,
        messenger: "ERRO",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});

module.exports = router;
