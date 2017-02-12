CREATE TABLE warehouseweighment (
    lotid uuid,
    grossweight double,
    netweight double,
    tareweight double,
    grossweightdate timestamp,
    tareweightdate timestamp,
    netweightdate timestamp,
    byuser text,
    PRIMARY KEY (lotid)
)