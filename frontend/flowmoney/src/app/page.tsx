import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { DollarSign } from "lucide-react";

export default function Home() {
  return (
    <main>
      <div className="flex items-center justify-center h-screen">
        <div className="p-8 space-y-8 max-w-md h-96 rounded-xl shadow-md bg-neutral-600 mx-auto">
          <span className="flex items-center gap-2">
            <DollarSign className="text-emerald-600" />
            <h1 className="uppercase text-slate-950 font-bold text-xl">
              Login Flow Money
            </h1>
          </span>
          <Input type="email" placeholder="Insira seu email" />
          <Input type="password" placeholder="Insira sua senha" />
          <Button className="hover:bg-blue-500">Enviar</Button>
        </div>
      </div>
    </main>
  );
}
