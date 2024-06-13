db.createUser(
    {
        user: "lotto-admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "lotto"
            }
        ]
    }
)