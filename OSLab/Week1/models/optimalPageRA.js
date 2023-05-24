const mongoose = require("mongoose");

const optimalPageRASchema = new mongoose.Schema({
    processes: [
        {
            type: Number
        }
    ],
    frames: Number,
    pageFaults: Number,
    pageFaultSequence: [
        {
            type: [
                {
                    type: Number
                }
            ]
        }
    ]
});

module.exports = mongoose.model("optimalPageRA", optimalPageRASchema);