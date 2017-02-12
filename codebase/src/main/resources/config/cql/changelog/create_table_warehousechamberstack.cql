CREATE TABLE warehousechamberstack (
    warehouselicenseno text,
    stackid uuid,
    capacityinbags double,
    chamberid uuid,
    chambername text,
    commoditycode uuid,
    fromdate timestamp,
    godownid uuid,
    godownname text,
    stackname text,
    stacktype text,
    todate timestamp,
    depositor UUID,
    PRIMARY KEY (warehouselicenseno, stackid)
)