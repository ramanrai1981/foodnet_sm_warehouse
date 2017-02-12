CREATE TABLE warehousetoken (
    warehouseid uuid,
    lotid uuid,
    wtoken int,
    PRIMARY KEY (warehouseid, wtoken)
) 