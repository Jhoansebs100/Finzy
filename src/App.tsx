import { useState, type ReactNode } from "react";

type Page = "home" | "send" | "learn" | "movements";
type Role = "minor" | "mentor";
type Session = "guest" | Role;
type IconName = "home" | "send" | "learn" | "chart" | "wallet" | "bell" | "eye" | "arrow" | "check" | "shield" | "search" | "plus" | "chevron" | "card" | "close" | "spark";

const iconPaths: Record<IconName, ReactNode> = {
  home: <><path d="m3 11 9-8 9 8" /><path d="M5 10v10h14V10M9 20v-6h6v6" /></>,
  send: <><path d="m22 2-7 20-4-9-9-4z" /><path d="M22 2 11 13" /></>,
  learn: <><path d="m12 3 9 5-9 5-9-5z" /><path d="M7 11v5c2 2 8 2 10 0v-5" /></>,
  chart: <><path d="M4 19V9M10 19V5M16 19v-7M22 19V3" /></>,
  wallet: <><path d="M4 6h14a2 2 0 0 1 2 2v11H4a2 2 0 0 1-2-2V6a3 3 0 0 1 3-3h12" /><path d="M16 12h6v4h-6a2 2 0 0 1 0-4" /></>,
  bell: <><path d="M18 8a6 6 0 0 0-12 0c0 7-3 7-3 9h18c0-2-3-2-3-9" /><path d="M10 21h4" /></>,
  eye: <><path d="M2 12s3.5-6 10-6 10 6 10 6-3.5 6-10 6S2 12 2 12" /><circle cx="12" cy="12" r="2.5" /></>,
  arrow: <><path d="m15 18-6-6 6-6" /><path d="M9 12h10" /></>,
  check: <path d="m5 12 4 4L19 6" />,
  shield: <><path d="M12 22s8-4 8-11V5l-8-3-8 3v6c0 7 8 11 8 11" /><path d="m9 12 2 2 4-4" /></>,
  search: <><circle cx="11" cy="11" r="7" /><path d="m20 20-4-4" /></>,
  plus: <><path d="M12 5v14M5 12h14" /></>,
  chevron: <path d="m9 18 6-6-6-6" />,
  card: <><rect x="3" y="5" width="18" height="14" rx="2" /><path d="M3 10h18" /></>,
  close: <><path d="m6 6 12 12M18 6 6 18" /></>,
  spark: <path d="m12 2 1.6 6.4L20 10l-6.4 1.6L12 18l-1.6-6.4L4 10l6.4-1.6z" />,
};

function Icon({ name, size = 20 }: { name: IconName; size?: number }) {
  return <svg width={size} height={size} viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">{iconPaths[name]}</svg>;
}

function Logo() {
  return <div className="logo"><img src="/finzy-logo.png" alt="Logo de Finzy" /><strong>Finzy</strong></div>;
}

const menu: { id: Page; label: string; icon: IconName }[] = [
  { id: "home", label: "Inicio", icon: "home" },
  { id: "send", label: "Enviar dinero", icon: "send" },
  { id: "movements", label: "Movimientos", icon: "chart" },
  { id: "learn", label: "Aprender", icon: "learn" },
];

function Sidebar({ page, onPage, onLogout }: { page: Page; onPage: (page: Page) => void; onLogout: () => void }) {
  return (
    <aside className="sidebar">
      <Logo />
      <nav>
        <span className="nav-label">MENÚ PRINCIPAL</span>
        {menu.map((item) => (
          <button className={page === item.id ? "active" : ""} onClick={() => onPage(item.id)} key={item.id}>
            <Icon name={item.icon} size={19} /><span>{item.label}</span>{page === item.id && <i />}
          </button>
        ))}
      </nav>
      <div className="sidebar-card">
        <span><Icon name="spark" size={17} /></span>
        <strong>Tu progreso</strong>
        <p>Completaste 4 de 8 lecciones este mes.</p>
        <div><i /></div>
        <small>50% completado</small>
      </div>
      <div className="sidebar-footer"><Icon name="shield" size={17} /><span><strong>Modo educativo</strong><small>Estás usando dinero ficticio</small></span></div>
      <button className="logout-btn" onClick={onLogout}><Icon name="arrow" size={16} /> Cerrar sesión</button>
    </aside>
  );
}

function AuthScreen({ onEnter }: { onEnter: (role: Role) => void }) {
  const [mode, setMode] = useState<"welcome" | "login" | "signup">("welcome");
  const [role, setRole] = useState<Role>("minor");
  const [showPassword, setShowPassword] = useState(false);

  if (mode === "welcome") {
    return (
      <main className="auth-page">
        <section className="auth-brand-panel">
          <div className="auth-top"><Logo /><span><Icon name="shield" size={15} /> Finanzas en un entorno seguro</span></div>
          <div className="auth-promise">
            <span className="auth-kicker">APRENDER · PRACTICAR · CRECER</span>
            <h1>Tu primera gran decisión financiera empieza <em>aquí.</em></h1>
            <p>Finzy conecta a jóvenes y mentores para aprender sobre dinero con práctica, acompañamiento y tranquilidad.</p>
            <div className="auth-benefits">
              <article><span><Icon name="learn" /></span><div><strong>Aprende haciendo</strong><p>Simulaciones y retos para dominar tus finanzas.</p></div></article>
              <article><span><Icon name="shield" /></span><div><strong>Crece acompañado</strong><p>Tu mentor puede orientarte sin invadir tu proceso.</p></div></article>
            </div>
          </div>
          <div className="auth-orbit" aria-hidden="true"><i /><i /><span className="auth-card-mini">FINZY<strong>•••• 7831</strong></span><b>$</b></div>
          <small className="auth-copyright">FINZY EDUCACIÓN FINANCIERA · 2025</small>
        </section>
        <section className="welcome-panel">
          <div className="welcome-box">
            <span className="welcome-icon"><img src="/finzy-logo.png" alt="" /></span>
            <span className="auth-kicker">TE DAMOS LA BIENVENIDA</span>
            <h2>Comienza tu camino con Finzy</h2>
            <p>Crea una cuenta para practicar o ingresa para continuar aprendiendo.</p>
            <button className="auth-primary" onClick={() => setMode("signup")}>Crear una cuenta <Icon name="chevron" size={17} /></button>
            <button className="auth-secondary" onClick={() => setMode("login")}>Ya tengo una cuenta</button>
            <div className="welcome-security"><Icon name="shield" size={17} /><span><strong>Tu información está protegida</strong><small>Finzy es una experiencia educativa. Nunca solicitamos datos bancarios reales.</small></span></div>
          </div>
        </section>
      </main>
    );
  }

  const isSignup = mode === "signup";
  return (
    <main className="auth-form-page">
      <aside className="form-aside">
        <Logo />
        <div>
          <span className="auth-kicker">{isSignup ? "UN NUEVO COMIENZO" : "QUÉ BUENO VERTE"}</span>
          <h1>{isSignup ? <>Aprende a usar tu dinero con <em>confianza.</em></> : <>Sigue construyendo tu <em>futuro.</em></>}</h1>
          <p>{isSignup ? "Crea tu espacio Finzy y empieza a practicar en compañía." : "Tus metas, aprendizajes y movimientos te están esperando."}</p>
        </div>
        <div className="aside-quote"><Icon name="spark" /><p>“Las buenas decisiones se entrenan. No tienes que saberlo todo para empezar.”</p></div>
      </aside>
      <section className="auth-form-wrap">
        <button className="auth-back" onClick={() => setMode("welcome")}><Icon name="arrow" size={16} /> Volver</button>
        <form className="auth-form" onSubmit={(event) => { event.preventDefault(); onEnter(role); }}>
          <span className="auth-kicker">{isSignup ? "CREA TU CUENTA" : "INICIA SESIÓN"}</span>
          <h2>{isSignup ? "¿Cómo quieres usar Finzy?" : "Te damos la bienvenida"}</h2>
          <p>{isSignup ? "Primero cuéntanos quién eres dentro de esta experiencia." : "Selecciona tu tipo de cuenta e ingresa tus datos."}</p>

          <fieldset className="role-selector">
            <legend>Tipo de cuenta</legend>
            <button type="button" className={role === "minor" ? "selected" : ""} onClick={() => setRole("minor")}>
              <span><Icon name="learn" size={20} /></span><div><strong>Soy menor de edad</strong><small>Quiero aprender y practicar</small></div><i>{role === "minor" && <Icon name="check" size={12} />}</i>
            </button>
            <button type="button" className={role === "mentor" ? "selected" : ""} onClick={() => setRole("mentor")}>
              <span><Icon name="shield" size={20} /></span><div><strong>Soy mentor</strong><small>Quiero acompañar y supervisar</small></div><i>{role === "mentor" && <Icon name="check" size={12} />}</i>
            </button>
          </fieldset>

          <div className="auth-fields">
            <label><span>Nombre completo</span><input required placeholder={role === "minor" ? "Ej. Valentina Suárez" : "Ej. Andrea Suárez"} /></label>
            <label><span>Correo electrónico</span><input required type="email" placeholder="nombre@correo.com" /></label>
            <label><span>Contraseña</span><div><input required type={showPassword ? "text" : "password"} placeholder="Mínimo 8 caracteres" minLength={8} /><button type="button" onClick={() => setShowPassword(!showPassword)} aria-label="Mostrar contraseña"><Icon name="eye" size={17} /></button></div></label>
            {isSignup && role === "minor" && <label className="mentor-link-field"><span>Usuario de tu mentor</span><div><input required placeholder="@usuario_mentor" /><Icon name="shield" size={17} /></div><small>Tu mentor recibirá una solicitud para enlazar las cuentas.</small></label>}
          </div>

          {!isSignup && <div className="form-options"><label><input type="checkbox" /> Recordarme</label><button type="button">Olvidé mi contraseña</button></div>}
          <button className="auth-primary submit" type="submit">{isSignup ? "Crear mi cuenta" : "Ingresar a Finzy"} <Icon name="chevron" size={17} /></button>
          <p className="switch-mode">{isSignup ? "¿Ya tienes una cuenta?" : "¿Aún no tienes una cuenta?"} <button type="button" onClick={() => setMode(isSignup ? "login" : "signup")}>{isSignup ? "Inicia sesión" : "Créala aquí"}</button></p>
          <small className="terms">Al continuar aceptas los términos de uso y la política de privacidad de Finzy.</small>
        </form>
      </section>
    </main>
  );
}

function Header({ title, subtitle }: { title: string; subtitle: string }) {
  return (
    <header className="topbar">
      <div><p>{subtitle}</p><h1>{title}</h1></div>
      <label className="search"><Icon name="search" size={17} /><input placeholder="Buscar en Finzy" /></label>
      <button className="icon-btn" aria-label="Notificaciones"><Icon name="bell" size={19} /><i /></button>
      <div className="profile"><span>VS</span><div><strong>Valentina Suárez</strong><small>Cuenta estudiante</small></div><Icon name="chevron" size={15} /></div>
    </header>
  );
}

function Home({ onSend, onLearn }: { onSend: () => void; onLearn: () => void }) {
  const [hidden, setHidden] = useState(false);
  return (
    <>
      <Header title="Hola, Valentina" subtitle="MIÉRCOLES, 18 DE JUNIO" />
      <div className="content dashboard">
        <section className="welcome-line"><div><h2>Tu dinero, más claro.</h2><p>Este es el resumen de tu semana financiera.</p></div><div className="safe-pill"><Icon name="shield" size={16} /> Entorno de práctica seguro</div></section>
        <section className="balance-card">
          <div className="balance-main">
            <span className="card-label">SALDO DISPONIBLE</span>
            <div className="balance"><strong>{hidden ? "$ ••••••" : "$ 450.000"}</strong><button onClick={() => setHidden(!hidden)} aria-label="Mostrar u ocultar saldo"><Icon name="eye" size={18} /></button></div>
            <p>Dinero ficticio para practicar</p>
            <div className="card-actions"><button onClick={onSend}><Icon name="send" size={17} />Enviar dinero</button><button><Icon name="plus" size={17} />Recargar saldo</button></div>
          </div>
          <div className="balance-visual"><span className="floating-coin coin-a">$</span><span className="floating-coin coin-b">$</span><div className="finzy-card"><span>FINZY / DÉBITO</span><i /><strong>•••• 7831</strong><small>VALENTINA SUÁREZ</small></div></div>
        </section>
        <section className="stats-grid">
          <article><span className="stat-icon income"><Icon name="wallet" /></span><div><small>INGRESOS ESTE MES</small><strong>$ 680.000</strong><p><b>+12%</b> vs. mes anterior</p></div></article>
          <article><span className="stat-icon spend"><Icon name="chart" /></span><div><small>GASTOS ESTE MES</small><strong>$ 230.000</strong><p><b className="neutral">34%</b> de tus ingresos</p></div></article>
          <article><span className="stat-icon saved"><Icon name="spark" /></span><div><small>AHORRO ACUMULADO</small><strong>$ 120.000</strong><p><b>+20.000</b> esta semana</p></div></article>
        </section>
        <div className="dashboard-columns">
          <section className="panel spending-panel">
            <div className="panel-title"><div><span>TU ACTIVIDAD</span><h3>Gastos de la semana</h3></div><select aria-label="Periodo"><option>Esta semana</option></select></div>
            <div className="chart-wrap">
              <div className="axis"><span>$80k</span><span>$60k</span><span>$40k</span><span>$20k</span><span>$0</span></div>
              <div className="bar-chart">
                {[["Lun", 38], ["Mar", 63], ["Mié", 31], ["Jue", 76], ["Vie", 51], ["Sáb", 88], ["Dom", 44]].map(([day, value], index) => <div className="bar-item" key={day}><i style={{ height: `${value}%` }} className={index === 5 ? "highlight" : ""} /><span>{day}</span></div>)}
              </div>
            </div>
            <div className="chart-note"><span><i /> Gastaste <strong>$18.500 menos</strong> que la semana pasada.</span><button>Ver análisis <Icon name="chevron" size={14} /></button></div>
          </section>
          <section className="panel movements">
            <div className="panel-title"><div><span>ÚLTIMOS DÍAS</span><h3>Movimientos recientes</h3></div><button>Ver todos</button></div>
            <Movement icon="wallet" title="Recarga de práctica" date="Hoy, 9:20 a. m." amount="+ $50.000" positive />
            <Movement icon="card" title="Almuerzo" date="Ayer, 1:05 p. m." amount="− $18.500" />
            <Movement icon="send" title="Envío a Carlos" date="16 jun, 5:42 p. m." amount="− $25.000" />
            <Movement icon="wallet" title="Transporte" date="15 jun, 7:30 a. m." amount="− $8.200" />
          </section>
        </div>
        <section className="lesson-banner">
          <div className="lesson-art"><span>$</span><i /></div>
          <div><span>LECCIÓN RECOMENDADA · 4 MIN</span><h3>El poder de un presupuesto</h3><p>Aprende a darle una misión a cada peso antes de gastarlo.</p></div>
          <div className="lesson-progress"><span>Tu progreso <b>50%</b></span><i><em /></i></div>
          <button onClick={onLearn}>Continuar lección <Icon name="chevron" size={16} /></button>
        </section>
      </div>
    </>
  );
}

function Movement({ icon, title, date, amount, positive = false }: { icon: IconName; title: string; date: string; amount: string; positive?: boolean }) {
  return <div className="movement"><span><Icon name={icon} size={18} /></span><div><strong>{title}</strong><small>{date}</small></div><b className={positive ? "positive" : ""}>{amount}</b></div>;
}

function SendPage({ onDone }: { onDone: () => void }) {
  const [step, setStep] = useState(1);
  const [amount, setAmount] = useState("35.000");
  return (
    <>
      <Header title="Enviar dinero" subtitle="TRANSFERENCIAS" />
      <div className="content send-page">
        <button className="back-link"><Icon name="arrow" size={16} /> Volver al inicio</button>
        <div className="send-layout">
          <section className="transfer-panel">
            <div className="stepper"><span className={step >= 1 ? "active" : ""}><b>1</b>Datos del envío</span><i /><span className={step >= 2 ? "active" : ""}><b>{step > 2 ? <Icon name="check" size={13} /> : "2"}</b>Confirmación</span><i /><span className={step >= 3 ? "active" : ""}><b>{step === 3 ? <Icon name="check" size={13} /> : "3"}</b>Resultado</span></div>
            {step === 1 && <div className="form-content">
              <span className="section-kicker">NUEVA TRANSFERENCIA</span><h2>¿Cuánto quieres enviar?</h2><p>Completa los datos. Recuerda que estás practicando con dinero ficticio.</p>
              <label className="field"><span>Monto a enviar</span><div className="amount-input"><b>$</b><input value={amount} onChange={(e) => setAmount(e.target.value.replace(/[^0-9.]/g, ""))} /></div><small>Saldo disponible: $450.000</small></label>
              <div className="chips">{["20.000", "50.000", "100.000"].map(v => <button key={v} onClick={() => setAmount(v)}>+ ${v}</button>)}</div>
              <div className="form-row"><label className="field"><span>Destinatario</span><button className="person-select"><i>LM</i><span><strong>Luisa Martínez</strong><small>300 123 4567</small></span><Icon name="chevron" size={16} /></button></label><label className="field"><span>Mensaje opcional</span><input className="text-input" placeholder="Ej. Para las onces" /></label></div>
              <div className="security-tip"><Icon name="shield" /><p><strong>Antes de continuar</strong>Verifica el nombre y el número. Una transferencia no se puede deshacer.</p></div>
              <div className="form-actions"><button className="secondary">Cancelar</button><button className="primary" onClick={() => setStep(2)}>Continuar <Icon name="chevron" size={16} /></button></div>
            </div>}
            {step === 2 && <div className="confirmation">
              <div className="confirm-icon"><Icon name="send" size={28} /></div><span className="section-kicker">REVISA ANTES DE ENVIAR</span><h2>Confirma tu transferencia</h2><p>Estás a un paso. Comprueba que todo esté correcto.</p>
              <div className="receipt"><div className="person"><i>LM</i><span><small>VAS A ENVIARLE A</small><strong>Luisa Martínez</strong><p>300 123 4567</p></span><button onClick={() => setStep(1)}>Editar</button></div><div><span>Valor a enviar</span><strong>${amount}</strong></div><div><span>Desde</span><b>Disponible · • 7831</b></div><div><span>Costo de la transferencia</span><b className="positive">$0</b></div></div>
              <div className="impact"><Icon name="learn" /><p><strong>Decisión informada</strong>Después de este envío te quedarán <b>$415.000</b> disponibles.</p></div>
              <div className="form-actions"><button className="secondary" onClick={() => setStep(1)}>Cambiar datos</button><button className="primary" onClick={() => setStep(3)}>Confirmar envío</button></div>
            </div>}
            {step === 3 && <div className="success">
              <div className="success-check"><Icon name="check" size={38} /></div><span className="section-kicker">TRANSFERENCIA EXITOSA</span><h2>¡Listo, Valentina!</h2><p>Le enviaste <strong>${amount}</strong> a Luisa Martínez.</p>
              <div className="success-summary"><div><span>Fecha y hora</span><strong>18 jun 2025 · 9:41 a. m.</strong></div><div><span>Comprobante</span><strong>FZ-4839201</strong></div></div>
              <div className="unlocked"><Icon name="spark" /><span><small>APRENDIZAJE DESBLOQUEADO</small><strong>Verificar antes de enviar</strong></span></div>
              <div className="form-actions center"><button className="secondary">Descargar comprobante</button><button className="primary" onClick={onDone}>Volver al inicio</button></div>
            </div>}
          </section>
          <aside className="transfer-aside">
            <span className="section-kicker">RESUMEN DE TU CUENTA</span><div className="mini-balance"><small>SALDO DISPONIBLE</small><strong>$450.000</strong><span>Cuenta Finzy · • 7831</span></div>
            <h3>Envía con seguridad</h3>
            <ul><li><Icon name="check" size={15} /> Confirma siempre el destinatario.</li><li><Icon name="check" size={15} /> Nunca compartas tus claves.</li><li><Icon name="check" size={15} /> Revisa el monto antes de enviar.</li></ul>
            <div className="practice-note"><Icon name="shield" /><p><strong>Estás en modo práctica</strong>Ningún movimiento usa dinero real.</p></div>
          </aside>
        </div>
      </div>
    </>
  );
}

function LearnPage() {
  return (
    <>
      <Header title="Centro de aprendizaje" subtitle="APRENDE A TU RITMO" />
      <div className="content learning-page">
        <section className="learning-hero"><div><span>FINZY ACADEMY</span><h2>Buenas decisiones hoy,<br /><em>tranquilidad mañana.</em></h2><p>Lecciones cortas y simuladores para entender tu dinero sin complicaciones.</p><button>Explorar rutas de aprendizaje <Icon name="chevron" size={16} /></button></div><div className="academy-visual"><i /><span className="book-card">FINANZAS<br /><b>PARA TI</b></span><span className="academy-coin">$</span></div></section>
        <section className="learning-stats"><article><strong>4</strong><span>Lecciones<br />completadas</span></article><article><strong>3</strong><span>Días seguidos<br />aprendiendo</span></article><article><strong>240</strong><span>Puntos Finzy<br />acumulados</span></article><div><span>PROGRESO GENERAL <b>50%</b></span><i><em /></i></div></section>
        <div className="learning-title"><div><span>CONTINÚA APRENDIENDO</span><h2>Lecciones recomendadas para ti</h2></div><button>Ver todas las lecciones</button></div>
        <section className="courses">
          <article className="featured-course"><div className="course-visual budget"><span>$</span></div><div><span>NIVEL BÁSICO · 4 MIN</span><h3>El poder de un presupuesto</h3><p>Aprende a darle una misión a cada peso.</p><div className="course-bar"><i /></div><small>50% completado</small></div><button aria-label="Continuar"><Icon name="chevron" /></button></article>
          <article><div className="course-visual secure"><Icon name="shield" size={30} /></div><div><span>SEGURIDAD · 3 MIN</span><h3>Que no te tumben</h3><p>Reconoce mensajes y llamadas sospechosas.</p><small>Sin comenzar</small></div><button aria-label="Abrir"><Icon name="chevron" /></button></article>
          <article><div className="course-visual saving"><Icon name="spark" size={30} /></div><div><span>AHORRO · 5 MIN</span><h3>Metas que sí se cumplen</h3><p>Crea un plan de ahorro realista.</p><small>Sin comenzar</small></div><button aria-label="Abrir"><Icon name="chevron" /></button></article>
        </section>
      </div>
    </>
  );
}

function MovementsPage() {
  return (
    <>
      <Header title="Movimientos" subtitle="TU ACTIVIDAD FINANCIERA" />
      <div className="content movement-page">
        <section className="movement-top"><div><span>TOTAL DISPONIBLE</span><strong>$450.000</strong><p>Actualizado hace un momento</p></div><button>Descargar reporte</button></section>
        <section className="panel movement-table"><div className="table-tools"><div><span>HISTORIAL</span><h2>Todos los movimientos</h2></div><label><Icon name="search" size={16} /><input placeholder="Buscar movimiento" /></label><select><option>Todos</option><option>Ingresos</option><option>Gastos</option></select></div>
          {[
            ["Hoy, 9:20 a. m.", "Recarga de práctica", "Recarga", "+ $50.000", true],
            ["Ayer, 1:05 p. m.", "Almuerzo", "Alimentación", "− $18.500", false],
            ["16 jun, 5:42 p. m.", "Envío a Carlos", "Transferencia", "− $25.000", false],
            ["15 jun, 7:30 a. m.", "Transporte", "Movilidad", "− $8.200", false],
            ["14 jun, 3:12 p. m.", "Ahorro para el viaje", "Ahorro", "− $20.000", false],
          ].map((row) => <div className="table-row" key={row[1] as string}><span>{row[0] as string}</span><strong>{row[1] as string}</strong><span className="category">{row[2] as string}</span><b className={row[4] ? "positive" : ""}>{row[3] as string}</b><button><Icon name="chevron" size={15} /></button></div>)}
        </section>
      </div>
    </>
  );
}

function MentorApp({ onLogout }: { onLogout: () => void }) {
  const [section, setSection] = useState<"overview" | "movements" | "deposit">("overview");
  const [depositOpen, setDepositOpen] = useState(false);
  const [depositDone, setDepositDone] = useState(false);
  const [depositAmount, setDepositAmount] = useState("50.000");

  const openDeposit = () => { setDepositDone(false); setDepositOpen(true); };
  return (
    <main className="mentor-shell">
      <aside className="mentor-sidebar">
        <Logo />
        <div className="mentor-account"><span>AS</span><div><strong>Andrea Suárez</strong><small>Cuenta de mentor</small></div></div>
        <nav>
          <span className="nav-label">ACOMPAÑAMIENTO</span>
          <button className={section === "overview" ? "active" : ""} onClick={() => setSection("overview")}><Icon name="home" size={18} />Resumen</button>
          <button className={section === "movements" ? "active" : ""} onClick={() => setSection("movements")}><Icon name="chart" size={18} />Movimientos</button>
          <button className={section === "deposit" ? "active" : ""} onClick={() => { setSection("deposit"); openDeposit(); }}><Icon name="wallet" size={18} />Hacer depósito</button>
        </nav>
        <div className="linked-profile"><span className="nav-label">CUENTA ENLAZADA</span><div><i>VS</i><span><strong>Valentina Suárez</strong><small>Menor · Activa</small></span><b /></div></div>
        <div className="mentor-side-tip"><Icon name="shield" /><p><strong>Acompaña, no controles</strong>Usa la información para conversar y enseñar.</p></div>
        <button className="logout-btn" onClick={onLogout}><Icon name="arrow" size={16} /> Cerrar sesión</button>
      </aside>

      <section className="mentor-workspace">
        <header className="mentor-header"><div><p>PANEL DEL MENTOR</p><h1>{section === "movements" ? "Movimientos de Valentina" : "Hola, Andrea"}</h1></div><div className="mentor-header-actions"><button className="icon-btn"><Icon name="bell" size={18} /><i /></button><button className="deposit-top" onClick={openDeposit}><Icon name="plus" size={16} /> Hacer un depósito</button></div></header>
        {section === "overview" && <div className="mentor-content">
          <section className="mentor-greeting"><div><span className="auth-kicker">RESUMEN DE VALENTINA</span><h2>Acompaña su progreso financiero</h2><p>Observa sus hábitos y encuentra momentos para conversar y aprender juntos.</p></div><div className="last-update"><i /><span><strong>Cuenta sincronizada</strong><small>Actualizada hace 2 minutos</small></span></div></section>
          <section className="mentor-stats">
            <article className="main-stat"><div><span>SALDO ACTUAL</span><strong>$450.000</strong><p>Dinero disponible en su cuenta Finzy</p></div><i><Icon name="wallet" size={26} /></i></article>
            <article><span>GASTADO ESTE MES</span><strong>$230.000</strong><p><b className="down">−8%</b> frente al mes pasado</p></article>
            <article><span>AHORRADO ESTE MES</span><strong>$120.000</strong><p><b>+20%</b> frente a su meta</p></article>
          </section>
          <div className="mentor-grid">
            <section className="panel mentor-chart">
              <div className="panel-title"><div><span>COMPORTAMIENTO FINANCIERO</span><h3>Dinero disponible y gastos</h3></div><select><option>Últimos 7 días</option><option>Este mes</option></select></div>
              <div className="chart-legend"><span><i className="plum-dot" />Saldo disponible</span><span><i className="coral-dot" />Gastos</span></div>
              <div className="line-chart">
                <div className="line-axis"><span>$500k</span><span>$400k</span><span>$300k</span><span>$200k</span><span>$100k</span></div>
                <svg viewBox="0 0 600 180" preserveAspectRatio="none" aria-label="Gráfica de dinero de Valentina">
                  <defs><linearGradient id="area" x1="0" x2="0" y1="0" y2="1"><stop offset="0%" stopColor="#4b1954" stopOpacity=".22" /><stop offset="100%" stopColor="#4b1954" stopOpacity="0" /></linearGradient></defs>
                  <path className="area" d="M0 36 C75 42 92 58 170 54 S265 73 340 68 S430 94 505 82 S565 92 600 89 L600 180 L0 180Z" />
                  <path className="balance-line" d="M0 36 C75 42 92 58 170 54 S265 73 340 68 S430 94 505 82 S565 92 600 89" />
                  <path className="expense-line" d="M0 151 C75 146 100 137 170 141 S270 125 340 130 S430 113 505 120 S565 106 600 110" />
                </svg>
                <div className="chart-days"><span>Lun</span><span>Mar</span><span>Mié</span><span>Jue</span><span>Vie</span><span>Sáb</span><span>Dom</span></div>
              </div>
              <div className="mentor-insight"><Icon name="spark" size={17} /><p><strong>Un buen momento para reconocerla:</strong> Valentina gastó 8% menos que el mes anterior.</p></div>
            </section>
            <section className="panel mentor-movements">
              <div className="panel-title"><div><span>ACTIVIDAD RECIENTE</span><h3>Últimos movimientos</h3></div><button onClick={() => setSection("movements")}>Ver todos</button></div>
              <Movement icon="wallet" title="Recarga de práctica" date="Hoy, 9:20 a. m." amount="+ $50.000" positive />
              <Movement icon="card" title="Almuerzo" date="Ayer, 1:05 p. m." amount="− $18.500" />
              <Movement icon="send" title="Envío a Carlos" date="16 jun, 5:42 p. m." amount="− $25.000" />
              <Movement icon="wallet" title="Transporte" date="15 jun, 7:30 a. m." amount="− $8.200" />
            </section>
          </div>
          <section className="mentor-bottom">
            <div className="goal-panel"><span className="goal-icon"><Icon name="spark" /></span><div><span>META DE AHORRO</span><h3>Viaje de fin de año</h3><p>Valentina lleva <strong>$120.000 de $300.000</strong></p><i><em /></i></div><b>40%</b></div>
            <div className="conversation-card"><Icon name="learn" /><div><span>CONVERSACIÓN SUGERIDA</span><h3>¿Qué diferencia un deseo de una necesidad?</h3><button>Ver guía de conversación <Icon name="chevron" size={14} /></button></div></div>
          </section>
        </div>}
        {section === "movements" && <div className="mentor-content"><section className="movement-top mentor"><div><span>SALDO DE VALENTINA</span><strong>$450.000</strong><p>Cuenta enlazada · • 7831</p></div><button onClick={openDeposit}>Hacer depósito</button></section><section className="panel movement-table"><div className="table-tools"><div><span>ACTIVIDAD DEL MENOR</span><h2>Historial de movimientos</h2></div><label><Icon name="search" size={16} /><input placeholder="Buscar movimiento" /></label><select><option>Todos</option><option>Ingresos</option><option>Gastos</option></select></div>{[["Hoy, 9:20 a. m.", "Recarga de práctica", "Recarga", "+ $50.000", true],["Ayer, 1:05 p. m.", "Almuerzo", "Alimentación", "− $18.500", false],["16 jun, 5:42 p. m.", "Envío a Carlos", "Transferencia", "− $25.000", false],["15 jun, 7:30 a. m.", "Transporte", "Movilidad", "− $8.200", false],["14 jun, 3:12 p. m.", "Ahorro para el viaje", "Ahorro", "− $20.000", false]].map(row => <div className="table-row" key={row[1] as string}><span>{row[0] as string}</span><strong>{row[1] as string}</strong><span className="category">{row[2] as string}</span><b className={row[4] ? "positive" : ""}>{row[3] as string}</b><button><Icon name="chevron" size={15} /></button></div>)}</section></div>}
      </section>

      {depositOpen && <div className="modal-backdrop" onMouseDown={(event) => event.target === event.currentTarget && setDepositOpen(false)}>
        <section className="deposit-modal">
          <button className="modal-close" onClick={() => setDepositOpen(false)}><Icon name="close" size={18} /></button>
          {!depositDone ? <>
            <span className="modal-icon"><Icon name="wallet" size={25} /></span><span className="auth-kicker">NUEVO DEPÓSITO</span><h2>Depositar a Valentina</h2><p>El dinero se sumará al saldo de práctica de la cuenta enlazada.</p>
            <div className="deposit-recipient"><i>VS</i><div><small>CUENTA DE DESTINO</small><strong>Valentina Suárez</strong><span>Finzy · • 7831</span></div><Icon name="check" size={18} /></div>
            <label className="field"><span>Monto del depósito</span><div className="amount-input"><b>$</b><input value={depositAmount} onChange={e => setDepositAmount(e.target.value.replace(/[^0-9.]/g, ""))} /></div></label>
            <div className="chips">{["20.000", "50.000", "100.000"].map(value => <button key={value} onClick={() => setDepositAmount(value)}>+ ${value}</button>)}</div>
            <label className="field"><span>Mensaje para Valentina (opcional)</span><input className="text-input" placeholder="Ej. Para tu meta de ahorro" /></label>
            <div className="practice-note"><Icon name="shield" /><p><strong>Depósito educativo</strong>Este movimiento utiliza saldo ficticio y quedará en el historial.</p></div>
            <button className="auth-primary submit" onClick={() => setDepositDone(true)}>Confirmar depósito</button>
          </> : <div className="deposit-success"><div className="success-check"><Icon name="check" size={36} /></div><span className="auth-kicker">DEPÓSITO EXITOSO</span><h2>¡Depósito realizado!</h2><p>Agregaste <strong>${depositAmount}</strong> a la cuenta de Valentina.</p><div><span>Nuevo saldo</span><strong>$500.000</strong></div><button className="auth-primary" onClick={() => { setDepositOpen(false); setSection("overview"); }}>Volver al resumen</button></div>}
        </section>
      </div>}
    </main>
  );
}

export default function App() {
  const [session, setSession] = useState<Session>("guest");
  const [page, setPage] = useState<Page>("home");
  if (session === "guest") return <AuthScreen onEnter={setSession} />;
  if (session === "mentor") return <MentorApp onLogout={() => setSession("guest")} />;
  return (
    <main className="app-shell">
      <Sidebar page={page} onPage={setPage} onLogout={() => { setSession("guest"); setPage("home"); }} />
      <section className="workspace">
        {page === "home" && <Home onSend={() => setPage("send")} onLearn={() => setPage("learn")} />}
        {page === "send" && <SendPage onDone={() => setPage("home")} />}
        {page === "learn" && <LearnPage />}
        {page === "movements" && <MovementsPage />}
      </section>
    </main>
  );
}
