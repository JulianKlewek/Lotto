db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "lotto"
            }
        ]
    }
)

db = new Mongo().getDB("lotto");

db.createCollection('WinningNumber');
db.createCollection('WinningNumberasda');

db.WinningNumber.insertMany([
    {
        _id: ObjectId(),
        numbers: [
            1, 2, 3, 4, 5, 6
        ],
        drawDate: ISODate('2024-06-07T20:00:00.000Z'),
        lotteryNumber: 1
    },
    {
        _id: ObjectId(),
        numbers: [
            1, 2, 3, 4, 7, 8
        ],
        drawDate: ISODate('2024-06-14T20:00:00.000Z'),
        lotteryNumber: 2
    }
]);