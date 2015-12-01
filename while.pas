program whi;
{const g = 10;}
var i : integer;
j : integer;
procedure proc1(h: integer);
function proc2(f: integer): integer;
begin
j := 5;
i := f + h;
proc2 := i;
end;
begin
j := 5;
i := j + h;
end;

begin
    proc1(10)
end.
     
