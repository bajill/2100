program fib;
const N = 40;

function fib1(x:Integer): Integer;
var f1: Integer; f2: Integer; f3: Integer; i: Integer;
begin
    i := 0;
    f1 := 0;
    f2 := 1;
    while i < x do begin
        f3 := f1 + f2;
        f1 := f2; 
        f2 := f3;
        i := i + 1;
    end;
    fib1 := f1
end;

function fib2(x:Integer): Integer;
begin
    if x <= 2 then fib2 := 1
    else fib2 := fib2(x-2) + fib2(x-1);
end;



begin
    write(fib2(N), eol);
    write(fib1(N), eol);
end.   
