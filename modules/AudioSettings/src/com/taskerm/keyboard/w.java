package com.joaomgcd.taskerm.keyboard;

import android.R;
import c.a.z;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7613a = new a(null);

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final Map<Integer, String> a() {
            return z.a(new c.k(Integer.valueOf(R.id.selectAll), "Select All"));
        }

        public final Map<Integer, String> b() {
            return z.a(new c.k(6, "Done"), new c.k(2, "Go"), new c.k(5, "Next"), new c.k(7, "Previous"), new c.k(3, "Search"), new c.k(4, "Send"));
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.w$a$a, reason: collision with other inner class name */
        public static final class C0186a extends HashMap<Integer, String> {
            public C0186a(c.k<Integer, String>... kVarArr) {
                c.f.b.k.b(kVarArr, "pairs");
                z.a(this, kVarArr);
            }

            public String a(Integer num, String str) {
                return (String) super.getOrDefault(num, str);
            }

            public Set a() {
                return super.entrySet();
            }

            public boolean a(Integer num) {
                return super.containsKey(num);
            }

            public String b(Integer num) {
                return (String) super.get(num);
            }

            public Set b() {
                return super.keySet();
            }

            public boolean b(Integer num, String str) {
                return super.remove(num, str);
            }

            public boolean b(String str) {
                return super.containsValue(str);
            }

            public String c(Integer num) {
                return (String) super.remove(num);
            }

            public Collection c() {
                return super.values();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final boolean containsKey(Object obj) {
                if (obj instanceof Integer) {
                    return a((Integer) obj);
                }
                return false;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final boolean containsValue(Object obj) {
                if (obj instanceof String) {
                    return b((String) obj);
                }
                return false;
            }

            public int d() {
                return super.size();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final Set<Map.Entry<Integer, String>> entrySet() {
                return a();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final Object get(Object obj) {
                if (obj instanceof Integer) {
                    return b((Integer) obj);
                }
                return null;
            }

            @Override // java.util.HashMap, java.util.Map
            public final Object getOrDefault(Object obj, Object obj2) {
                return obj instanceof Integer ? a((Integer) obj, (String) obj2) : obj2;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final Set<Integer> keySet() {
                return b();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final Object remove(Object obj) {
                if (obj instanceof Integer) {
                    return c((Integer) obj);
                }
                return null;
            }

            @Override // java.util.HashMap, java.util.Map
            public final boolean remove(Object obj, Object obj2) {
                if ((obj instanceof Integer) && (obj2 instanceof String)) {
                    return b((Integer) obj, (String) obj2);
                }
                return false;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final int size() {
                return d();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final Collection<String> values() {
                return c();
            }

            public final Integer a(String str) {
                c.f.b.k.b(str, "description");
                for (Map.Entry<Integer, String> entry : entrySet()) {
                    if (c.f.b.k.a((Object) entry.getValue(), (Object) str)) {
                        return entry.getKey();
                    }
                }
                return null;
            }
        }

        public final C0186a c() {
            return new C0186a(new c.k(7, "0"), new c.k(8, "1"), new c.k(227, "11"), new c.k(228, "12"), new c.k(9, "2"), new c.k(10, "3"), new c.k(206, "3d Mode"), new c.k(11, "4"), new c.k(12, "5"), new c.k(13, "6"), new c.k(14, "7"), new c.k(15, "8"), new c.k(16, "9"), new c.k(29, "A"), new c.k(284, "All Apps"), new c.k(75, "Apostrophe"), new c.k(187, "App Switch"), new c.k(219, "Assist"), new c.k(77, "At"), new c.k(182, "Avr Input"), new c.k(181, "Avr Power"), new c.k(30, "B"), new c.k(4, "Back"), new c.k(73, "Backslash"), new c.k(174, "Bookmark"), new c.k(121, "Break"), new c.k(220, "Brightness Down"), new c.k(221, "Brightness Up"), new c.k(188, "Button 1"), new c.k(197, "Button 10"), new c.k(198, "Button 11"), new c.k(199, "Button 12"), new c.k(200, "Button 13"), new c.k(201, "Button 14"), new c.k(202, "Button 15"), new c.k(203, "Button 16"), new c.k(189, "Button 2"), new c.k(190, "Button 3"), new c.k(191, "Button 4"), new c.k(192, "Button 5"), new c.k(193, "Button 6"), new c.k(194, "Button 7"), new c.k(195, "Button 8"), new c.k(196, "Button 9"), new c.k(96, "Button A"), new c.k(97, "Button B"), new c.k(98, "Button C"), new c.k(102, "Button L1"), new c.k(104, "Button L2"), new c.k(110, "Button Mode"), new c.k(103, "Button R1"), new c.k(105, "Button R2"), new c.k(109, "Button Select"), new c.k(108, "Button Start"), new c.k(106, "Button Thumbl"), new c.k(107, "Button Thumbr"), new c.k(99, "Button X"), new c.k(100, "Button Y"), new c.k(101, "Button Z"), new c.k(31, "C"), new c.k(210, "Calculator"), new c.k(208, "Calendar"), new c.k(5, "Call"), new c.k(27, "Camera"), new c.k(115, "Caps Lock"), new c.k(175, "Captions"), new c.k(167, "Channel Down"), new c.k(166, "Channel Up"), new c.k(28, "Clear"), new c.k(55, "Comma"), new c.k(207, "Contacts"), new c.k(278, "Copy"), new c.k(277, "Cut"), new c.k(32, "D"), new c.k(67, "Del"), new c.k(23, "Dpad Center"), new c.k(20, "Dpad Down"), new c.k(269, "Dpad Down Left"), new c.k(271, "Dpad Down Right"), new c.k(21, "Dpad Left"), new c.k(22, "Dpad Right"), new c.k(19, "Dpad Up"), new c.k(268, "Dpad Up Left"), new c.k(270, "Dpad Up Right"), new c.k(173, "Dvr"), new c.k(33, "E"), new c.k(212, "Eisu"), new c.k(6, "Endcall"), new c.k(66, "Enter"), new c.k(65, "Envelope"), new c.k(70, "Equals"), new c.k(111, "Escape"), new c.k(64, "Explorer"), new c.k(34, "F"), new c.k(131, "F1"), new c.k(140, "F10"), new c.k(141, "F11"), new c.k(142, "F12"), new c.k(132, "F2"), new c.k(133, "F3"), new c.k(134, "F4"), new c.k(135, "F5"), new c.k(136, "F6"), new c.k(137, "F7"), new c.k(138, "F8"), new c.k(139, "F9"), new c.k(80, "Focus"), new c.k(125, "Forward"), new c.k(112, "Forward Del"), new c.k(119, "Function"), new c.k(35, "G"), new c.k(68, "Grave"), new c.k(172, "Guide"), new c.k(36, "H"), new c.k(79, "Headsethook"), new c.k(259, "Help"), new c.k(214, "Henkan"), new c.k(3, "Home"), new c.k(37, "I"), new c.k(165, "Info"), new c.k(124, "Insert"), new c.k(38, "J"), new c.k(39, "K"), new c.k(218, "Kana"), new c.k(215, "Katakana Hiragana"), new c.k(40, "L"), new c.k(204, "Language Switch"), new c.k(229, "Last Channel"), new c.k(71, "Left Bracket"), new c.k(41, "M"), new c.k(205, "Manner Mode"), new c.k(222, "Media Audio Track"), new c.k(128, "Media Close"), new c.k(129, "Media Eject"), new c.k(90, "Media Fast Forward"), new c.k(87, "Media Next"), new c.k(127, "Media Pause"), new c.k(126, "Media Play"), new c.k(85, "Media Play Pause"), new c.k(88, "Media Previous"), new c.k(130, "Media Record"), new c.k(89, "Media Rewind"), new c.k(273, "Media Skip Backward"), new c.k(272, "Media Skip Forward"), new c.k(275, "Media Step Backward"), new c.k(274, "Media Step Forward"), new c.k(86, "Media Stop"), new c.k(226, "Media Top Menu"), new c.k(82, "Menu"), new c.k(117, "Meta Left"), new c.k(118, "Meta Right"), new c.k(69, "Minus"), new c.k(123, "Move End"), new c.k(122, "Move Home"), new c.k(213, "Muhenkan"), new c.k(209, "Music"), new c.k(91, "Mute"), new c.k(42, "N"), new c.k(262, "Navigate In"), new c.k(261, "Navigate Next"), new c.k(263, "Navigate Out"), new c.k(260, "Navigate Previous"), new c.k(83, "Notification"), new c.k(78, "Num"), new c.k(144, "Numpad 0"), new c.k(145, "Numpad 1"), new c.k(146, "Numpad 2"), new c.k(147, "Numpad 3"), new c.k(148, "Numpad 4"), new c.k(149, "Numpad 5"), new c.k(150, "Numpad 6"), new c.k(151, "Numpad 7"), new c.k(152, "Numpad 8"), new c.k(153, "Numpad 9"), new c.k(157, "Numpad Add"), new c.k(159, "Numpad Comma"), new c.k(154, "Numpad Divide"), new c.k(158, "Numpad Dot"), new c.k(160, "Numpad Enter"), new c.k(161, "Numpad Equals"), new c.k(162, "Numpad Left Paren"), new c.k(155, "Numpad Multiply"), new c.k(163, "Numpad Right Paren"), new c.k(156, "Numpad Subtract"), new c.k(143, "Num Lock"), new c.k(43, "O"), new c.k(44, "P"), new c.k(93, "Page Down"), new c.k(92, "Page Up"), new c.k(225, "Pairing"), new c.k(279, "Paste"), new c.k(56, "Period"), new c.k(94, "Pictsymbols"), new c.k(81, "Plus"), new c.k(18, "Pound"), new c.k(26, "Power"), new c.k(186, "Prog Blue"), new c.k(184, "Prog Green"), new c.k(183, "Prog Red"), new c.k(185, "Prog Yellow"), new c.k(45, "Q"), new c.k(46, "R"), new c.k(285, "Refresh"), new c.k(72, "Right Bracket"), new c.k(217, "Ro"), new c.k(47, "S"), new c.k(116, "Scroll Lock"), new c.k(74, "Semicolon"), new c.k(176, "Settings"), new c.k(76, "Slash"), new c.k(223, "Sleep"), new c.k(1, "Soft Left"), new c.k(2, "Soft Right"), new c.k(276, "Soft Sleep"), new c.k(62, "Space"), new c.k(17, "Star"), new c.k(180, "Stb Input"), new c.k(179, "Stb Power"), new c.k(265, "Stem 1"), new c.k(266, "Stem 2"), new c.k(267, "Stem 3"), new c.k(264, "Stem Primary"), new c.k(95, "Switch Charset"), new c.k(63, "Sym"), new c.k(120, "Sysrq"), new c.k(281, "System Navigation Down"), new c.k(282, "System Navigation Left"), new c.k(283, "System Navigation Right"), new c.k(280, "System Navigation Up"), new c.k(48, "T"), new c.k(61, "Tab"), new c.k(170, "Tv"), new c.k(242, "Tv Antenna Cable"), new c.k(252, "Tv Audio Description"), new c.k(254, "Tv Audio Description Mix Down"), new c.k(253, "Tv Audio Description Mix Up"), new c.k(256, "Tv Contents Menu"), new c.k(230, "Tv Data Service"), new c.k(178, "Tv Input"), new c.k(249, "Tv Input Component 1"), new c.k(250, "Tv Input Component 2"), new c.k(247, "Tv Input Composite 1"), new c.k(248, "Tv Input Composite 2"), new c.k(243, "Tv Input Hdmi 1"), new c.k(244, "Tv Input Hdmi 2"), new c.k(245, "Tv Input Hdmi 3"), new c.k(246, "Tv Input Hdmi 4"), new c.k(251, "Tv Input Vga 1"), new c.k(257, "Tv Media Context Menu"), new c.k(241, "Tv Network"), new c.k(234, "Tv Number Entry"), new c.k(177, "Tv Power"), new c.k(232, "Tv Radio Service"), new c.k(237, "Tv Satellite"), new c.k(238, "Tv Satellite Bs"), new c.k(239, "Tv Satellite Cs"), new c.k(240, "Tv Satellite Service"), new c.k(233, "Tv Teletext"), new c.k(235, "Tv Terrestrial Analog"), new c.k(236, "Tv Terrestrial Digital"), new c.k(258, "Tv Timer Programming"), new c.k(255, "Tv Zoom Mode"), new c.k(49, "U"), new c.k(50, "V"), new c.k(231, "Voice Assist"), new c.k(25, "Volume Down"), new c.k(164, "Volume Mute"), new c.k(24, "Volume Up"), new c.k(51, "W"), new c.k(224, "Wakeup"), new c.k(171, "Window"), new c.k(52, "X"), new c.k(53, "Y"), new c.k(216, "Yen"), new c.k(54, "Z"), new c.k(211, "Zenkaku Hankaku"), new c.k(168, "Zoom In"), new c.k(169, "Zoom Out"));
        }
    }
}
